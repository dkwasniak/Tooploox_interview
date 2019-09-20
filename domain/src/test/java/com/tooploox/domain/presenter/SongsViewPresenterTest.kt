package com.tooploox.domain.presenter

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.tooploox.domain.interactor.MainInteractor
import com.tooploox.domain.view.SongsView
import com.tooploox.domain.viewmodel.SongViewModel
import com.tooploox.domain.viewmodel.SongsViewModel
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class SongsViewPresenterTest : BasePresenterTest() {

    @Mock
    lateinit var mainInteractor: MainInteractor

    @Mock
    lateinit var songsView: SongsView

    private lateinit var target: SongsViewPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        target = SongsViewPresenter(mainInteractor, schedulersProvider)
        target.attach(songsView)
    }

    @Test
    fun `view should show error when interactor fails`() {
        //given

        //when
        whenever(mainInteractor.fetchSongs(
            isiTunesSelected = true,
            isLocalSelected = true,
            thirdSelected = isThiredSelected
        ))
                .thenReturn(Single.error(java.lang.Exception()))
        target.fetchSongs(
            isiTunesSelected = true,
            isLocalSelected = true,
            isThiredSelected = intent?.extras?.getBoolean(THIRD_KEY) ?: false
        )

        //then
        verify(songsView).showError()
    }

    @Test
    fun `view should show songs when interactor completed`() {
        //given
        val songsList = listOf<SongViewModel>()
        val songsViewModel = SongsViewModel(songsList)

        //when
        whenever(mainInteractor.fetchSongs(
            isiTunesSelected = true,
            isLocalSelected = true,
            thirdSelected = isThiredSelected
        )).thenReturn(Single.just(songsViewModel))
        target.fetchSongs(
            isiTunesSelected = true,
            isLocalSelected = true,
            isThiredSelected = intent?.extras?.getBoolean(THIRD_KEY) ?: false
        )

        //then
        verify(songsView).showSongs(any())
    }

}