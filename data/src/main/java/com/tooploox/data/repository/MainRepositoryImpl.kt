package com.tooploox.data.repository

import com.tooploox.data.SongsDataModelsToDomainModelMapper
import com.tooploox.data.api.MainApiService
import com.tooploox.data.api.model.SongApiModel
import com.tooploox.data.db.LocalSongsService
import com.tooploox.data.db.model.SongDbModel
import com.tooploox.domain.repository.MainRepository
import com.tooploox.domainmodule.SongDomainModel
import com.tooploox.domainmodule.SongsDomainModel
import io.reactivex.Observable
import io.reactivex.functions.BiFunction

class MainRepositoryImpl(
        private val mainApiService: MainApiService,
        private val localSongsService: LocalSongsService,
        private val songsMapper: SongsDataModelsToDomainModelMapper
) : MainRepository {

    override fun fetchSongs(isiTunesSelected: Boolean, localSelected: Boolean): Observable<SongsDomainModel> {
        return if (isiTunesSelected && localSelected) {
            Observable.zip(fetchApiSongs(), fetchLocalSongs(), BiFunction { apiSongs, localSongs ->
                val list = mutableListOf<SongDomainModel>()
                list.addAll(songsMapper.mapSongApiModelToDomainModel(apiSongs))
                list.addAll(songsMapper.mapSongDbModelToDomainModel(localSongs))
                SongsDomainModel(list)
            })
        } else if (isiTunesSelected) {
            fetchApiSongs().map {
                songsMapper.mapSongApiModelListToSongsDomainModel(it)
            }
        } else {
            fetchLocalSongs().map {
                songsMapper.mapSongDbModelListToSongsDomainModel(it)
            }
        }
    }

    private fun fetchLocalSongs(): Observable<List<SongDbModel>> {
        return localSongsService.fetchSongs().map { it.songs }
    }

    private fun fetchApiSongs(): Observable<List<SongApiModel>> {
        return mainApiService.fetchSongs().flatMap { response ->
            if (response.isSuccessful) {
                Observable.just(response.body()).map { it.results }
            } else {
                Observable.empty()
            }
        }
    }

}