package com.nomtek.domain.presenter

import com.nhaarman.mockito_kotlin.verify
import com.tooploox.domain.interactor.MainInteractor
import com.tooploox.domain.presenter.SongsViewPresenter
import com.tooploox.domain.view.SongsView
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.never
import org.mockito.MockitoAnnotations
import org.mockito.internal.verification.Times
import rx.Observable


class MainViewPresenterTest {

    private lateinit var target: SongsViewPresenter

    @Mock
    lateinit var mainInteractor: MainInteractor

    @Mock
    lateinit var view: SongsView

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        target = SongsViewPresenter(mainInteractor)
        target.attach(view)

    }

    @Test
    fun checkIfPostsAreSetOnSuccessResult() {
        //given
        `when`(mainInteractor.getPosts()).thenReturn(Observable.just(ArrayList()))

        //when
        target.getPosts()

        //then
        verify(view).loadData(ArgumentMatchers.anyList())
        verify(view, Times(1)).loadData(ArgumentMatchers.anyList())
    }

    @Test
    fun checkIfErrorIsShownWhenInteractorFails() {
        //given
        val exception = Exception()
        `when`(mainInteractor.getPosts()).thenReturn(Observable.error(exception))

        //when
        target.getPosts()

        //then
        verify(view, Times(1)).showError(exception)
        verify(view, never()).loadData(ArgumentMatchers.anyList())
    }
}
