package com.tooploox.domain.interactor

import com.nhaarman.mockito_kotlin.whenever
import com.tooploox.domain.mapper.SongsDomainModelToViewModelMapper
import com.tooploox.domain.repository.MainRepository
import com.tooploox.domain.viewmodel.SongViewModel
import com.tooploox.domain.viewmodel.SongsViewModel
import com.tooploox.domainmodule.SongDomainModel
import com.tooploox.domainmodule.SongsDomainModel
import com.tooploox.domainmodule.Source
import io.reactivex.Single
import io.reactivex.subscribers.TestSubscriber
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class MainInteractorTest {

    lateinit var target: MainInteractor

    @Mock
    lateinit var mainRepository: MainRepository

    @Mock
    lateinit var mapper: SongsDomainModelToViewModelMapper

    var testSubscriber = TestSubscriber<SongViewModel>()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        target = MainInteractor(mainRepository, mapper)
    }

    @Test
    fun `fetchSongs should return proper view model`() {
        //given
        val domainResult = mockDomainModel()
        val viewModel = mockViewModel()
        //when
        whenever(mainRepository.fetchSongs(isiTunesSelected = true, localSelected = true))
                .thenReturn(Single.just(domainResult))
        whenever(mapper.mapSongsDomainModelToViewModel(domainResult)).thenReturn(viewModel)
        val observer = target.fetchSongs(true, true, isThiredSelected).test()

        //then
        observer.assertNoErrors()
        observer.assertComplete()
    }

    private fun mockDomainModel(): SongsDomainModel {
        return SongsDomainModel(listOf(SongDomainModel("title", "artistName",
                "1923", Source.ITUNES, "")))
    }

    private fun mockViewModel(): SongsViewModel {
        return SongsViewModel(listOf(SongViewModel("title", "artistName",
                "1923", Source.ITUNES, "")))
    }
}