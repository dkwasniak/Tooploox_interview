package com.tooploox.domain.mapper

import com.tooploox.domain.viewmodel.SongViewModel
import com.tooploox.domain.viewmodel.SongsViewModel
import com.tooploox.domainmodule.SongsDomainModel
import javax.inject.Inject

open class SongsDomainModelToViewModelMapper @Inject constructor() {
    open fun mapSongsDomainModelToViewModel(songsDomainModel: SongsDomainModel): SongsViewModel {
        return SongsViewModel(mapSongDomainModelListToViewModel(songsDomainModel))
    }

    private fun mapSongDomainModelListToViewModel(songsDomainModel: SongsDomainModel):
    List<SongViewModel> {
        return songsDomainModel.songs.map {
            SongViewModel(
                    title = it.title,
                    artistName = it.artistName,
                    releaseData = it.releaseDate,
                    source = it.source,
                    coverImageUrl = it.coverImageUrl
            )
        }.sortedBy { it.title }
    }


}