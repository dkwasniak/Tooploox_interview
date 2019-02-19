package com.tooploox.data.repository

import com.nhaarman.mockito_kotlin.verify
import com.tooploox.data.api.MainApiService
import com.tooploox.data.createSuccessResponse
import com.tooploox.domain.api.model.PostApiModel
import com.tooploox.domain.repository.MainRepository
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import rx.Observable
import rx.observers.TestSubscriber
import kotlin.test.assertEquals

class MainRepositoryImplTest {

    private lateinit var target: MainRepository

    @Mock
    lateinit var mainApiService: MainApiService

    private var testSubscriber: TestSubscriber<List<PostApiModel>> = TestSubscriber()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        target = MainRepositoryImpl(mainApiService)
    }

    @Test
    fun testPositiveCase() {
        //given
        val result = listOf(PostApiModel())
        `when`(mainApiService.getPosts()).thenReturn(Observable.just(createSuccessResponse(200, result)))

        //when
        target.getPosts().subscribe(testSubscriber)

        //then
        verify(mainApiService).getPosts()
        testSubscriber.assertNoErrors()
        assertEquals<List<PostApiModel>>(testSubscriber.onNextEvents[0], result)
        testSubscriber.assertCompleted()
    }


    @Test
    fun testNegativeCase() {
        //given
        val error = Exception()
        `when`(mainApiService.getPosts()).thenReturn(Observable.error(error))

        //when
        target.getPosts().subscribe(testSubscriber)

        //then
        verify(mainApiService).getPosts()
        testSubscriber.assertError(error)
        testSubscriber.assertNotCompleted()
    }


}