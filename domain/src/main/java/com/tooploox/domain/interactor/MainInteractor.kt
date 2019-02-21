package com.tooploox.domain.interactor

import com.tooploox.domain.mapper.SongsDomainModelToViewModelMapper
import com.tooploox.domain.repository.MainRepository
import com.tooploox.domain.viewmodel.SongsViewModel
import io.reactivex.Observable
import javax.inject.Inject

open class MainInteractor @Inject constructor(
    private val mainRepository: MainRepository,
    private val mapper: SongsDomainModelToViewModelMapper
) {
    fun fetchSongs(isiTunesSelected: Boolean, isLocalSelected: Boolean):
            Observable<SongsViewModel> {
        return mainRepository.fetchSongs(isiTunesSelected, isLocalSelected).map {
            mapper.mapSongsDomainModelToViewModel(it)
        }
    }

}