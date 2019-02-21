package com.tooploox.domain.repository

import com.tooploox.domainmodule.SongsDomainModel
import io.reactivex.Single

interface MainRepository {

    fun fetchSongs(isiTunesSelected: Boolean, localSelected: Boolean): Single<SongsDomainModel>
}