package com.tooploox.data.repository

import com.tooploox.data.api.MainApiService
import com.tooploox.data.api.model.SongApiModel
import com.tooploox.data.db.model.SongDbModel
import com.tooploox.data.mapper.SongsDataModelsToDomainModelMapper
import com.tooploox.data.service.LocalSongsService
import com.tooploox.domain.presenter.SourceType
import com.tooploox.domain.repository.MainRepository
import com.tooploox.domainmodule.SongDomainModel
import com.tooploox.domainmodule.SongsDomainModel
import io.reactivex.Single

class MainRepositoryImpl(
    private val mainApiService: MainApiService,
    private val localSongsService: LocalSongsService,
    private val songsMapper: SongsDataModelsToDomainModelMapper
) : MainRepository {

    override fun fetchSongs(listOfSources: List<SourceType>): Single<SongsDomainModel> {
        return Single.merge(provideListOfSelectedSources(listOfSources)).map {
            SongsDomainModel(it)
        }.singleOrError()
    }

    private fun provideListOfSelectedSources(listOfSources: List<SourceType>): List<Single<List<SongDomainModel>>> {
        return listOfSources.map {
            when (it) {
                SourceType.ITUNES -> fetchApiSongs()
                SourceType.LOCAL -> fetchLocalSongs()
                SourceType.THIRD -> fetchThirdSongs()
            }
        }
    }

    private fun mergeSources(
        apiSongs: List<SongApiModel>,
        localSongs: List<SongDbModel>,
        thirdSongs: List<SongDbModel>
    ): List<SongDomainModel> {
        val list = mutableListOf<SongDomainModel>()
        list.addAll(songsMapper.mapSongApiModelToDomainModel(apiSongs))
        list.addAll(songsMapper.mapSongDbModelToDomainModel(localSongs))
        return songsMapper.mapSongApiModelToDomainModel(apiSongs) +
                songsMapper.mapSongDbModelToDomainModel(localSongs) +
                songsMapper.mapSongDbModelToDomainModel(thirdSongs)
    }

    private fun fetchLocalSongs(): Single<List<SongDomainModel>> {
        return localSongsService.fetchSongs().map { it.songs }.map { songsMapper.mapSongDbModelToDomainModel(it) }
    }

    private fun fetchThirdSongs(): Single<List<SongDomainModel>> {
        return localSongsService.fetchSongs().map { it.songs }.map { songsMapper.mapSongDbModelToDomainModel(it) }
    }

    private fun fetchApiSongs(): Single<List<SongDomainModel>> {
        return mainApiService.fetchSongs().flatMap { response ->
            if (response.isSuccessful) {
                Single.just(response.body()).map { it.results }.map { songsMapper.mapSongApiModelToDomainModel(it) }
            } else {
                Single.error(Exception())
            }
        }
    }

}