package com.tooploox.data.mapper

import com.tooploox.data.api.model.SongApiModel
import com.tooploox.data.db.model.SongDbModel
import com.tooploox.domainmodule.SongDomainModel
import com.tooploox.domainmodule.SongsDomainModel
import com.tooploox.domainmodule.Source
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

open class SongsDataModelsToDomainModelMapper @Inject constructor() {

    private val formatter: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX", Locale("pl_PL"))

    private val calendar = Calendar.getInstance()


    fun mapSongApiModelListToSongsDomainModel(list: List<SongApiModel>): SongsDomainModel {
        return SongsDomainModel(mapSongApiModelToDomainModel(list))
    }

    fun mapSongDbModelListToSongsDomainModel(list: List<SongDbModel>): SongsDomainModel {
        return SongsDomainModel(mapSongDbModelToDomainModel(list))
    }

    fun mapSongApiModelToDomainModel(list: List<SongApiModel>): List<SongDomainModel> {
        return list.map {
            calendar.time = formatter.parse(it.releaseData)
            SongDomainModel(
                    title = it.trackName,
                    artistName = it.artistName,
                    releaseDate = calendar.get(Calendar.YEAR).toString(),
                    source = Source.ITUNES,
                    coverImageUrl = it.coverImageUrl
            )
        }
    }

    fun mapSongDbModelToDomainModel(list: List<SongDbModel>): List<SongDomainModel> {
        return list.map {
            SongDomainModel(
                    title = it.songClean,
                    releaseDate = it.releaseDate,
                    artistName = it.artistClean,
                    source = Source.LOCAL
            )
        }
    }
}