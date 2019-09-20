package com.tooploox.domain.interactor

import com.tooploox.domain.mapper.SongsDomainModelToViewModelMapper
import com.tooploox.domain.presenter.SourceType
import com.tooploox.domain.repository.MainRepository
import com.tooploox.domain.viewmodel.SongsViewModel
import io.reactivex.Single
import javax.inject.Inject

open class MainInteractor @Inject constructor(
    private val mainRepository: MainRepository,
    private val mapper: SongsDomainModelToViewModelMapper
) {

    open fun fetchSongs(listOfSources: List<SourceType>):
            Single<SongsViewModel> {
        return mainRepository.fetchSongs(listOfSources).map {
            mapper.mapSongsDomainModelToViewModel(it)
        }
    }
}
