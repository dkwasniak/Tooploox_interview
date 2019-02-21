package com.tooploox.data

import com.tooploox.data.api.model.SongApiModel
import com.tooploox.data.db.model.SongDbModel
import com.tooploox.domainmodule.SongDomainModel
import com.tooploox.domainmodule.SongsDomainModel
import javax.inject.Inject

class SongsDataModelsToDomainModelMapper @Inject constructor() {

    fun mapSongApiModelListToSongsDomainModel(list: List<SongApiModel>): SongsDomainModel {
        return SongsDomainModel(mapSongApiModelToDomainModel(list))
    }

    fun mapSongDbModelListToSongsDomainModel(list: List<SongDbModel>): SongsDomainModel {
        return SongsDomainModel(mapSongDbModelToDomainModel(list))
    }

    fun mapSongApiModelToDomainModel(list: List<SongApiModel>): List<SongDomainModel> {
        return list.map {
            SongDomainModel(
                title = it.trackName,
                artistName = it.artistName
            )
        }
    }

    fun mapSongDbModelToDomainModel(list: List<SongDbModel>): List<SongDomainModel> {
        return list.map {
            SongDomainModel(
                title = it.songClean,
                artistName = it.artistClean
            )
        }
    }
}