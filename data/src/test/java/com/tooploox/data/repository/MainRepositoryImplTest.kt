package com.tooploox.data.repository

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.tooploox.data.api.MainApiService
import com.tooploox.data.api.model.SongsApiModel
import com.tooploox.data.createErrorResponse
import com.tooploox.data.createSuccessResponse
import com.tooploox.data.db.model.SongsDbModel
import com.tooploox.data.mapper.SongsDataModelsToDomainModelMapper
import com.tooploox.data.service.LocalSongsService
import com.tooploox.domain.repository.MainRepository
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class MainRepositoryImplTest {

    private lateinit var target: MainRepository

    @Mock
    lateinit var mainApiService: MainApiService

    @Mock
    lateinit var localSongsService: LocalSongsService

    var songsMapper: SongsDataModelsToDomainModelMapper = SongsDataModelsToDomainModelMapper()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        target = MainRepositoryImpl(mainApiService, localSongsService, songsMapper)
    }

    @Test
    fun `repository should successed when data layer success`() {
        //given
        val result = SongsApiModel(0, listOf())
        val songsDbModel = SongsDbModel(listOf())

        //when
        whenever(localSongsService.fetchSongs()).thenReturn(Single.just(songsDbModel))
        whenever(mainApiService.fetchSongs()).thenReturn(Single.just(createSuccessResponse(200, result)))
        val observer = target.fetchSongs(true, true).test()

        //then
        observer.assertNoErrors()
       // observer.assertComplete()
    }


    @Test
    fun `repository should fail when data layer failing`() {
        //given
        val error = Exception()
        val songsDbModel = SongsDbModel(listOf())

        //when
        whenever(localSongsService.fetchSongs()).thenReturn(Single.just(songsDbModel))
        whenever(mainApiService.fetchSongs()).thenReturn(Single.error(error))
        val observer = target.fetchSongs(true, true).test()

        //then
        verify(mainApiService).fetchSongs()
        observer.assertError(error)
        observer.assertNotComplete()
    }


}