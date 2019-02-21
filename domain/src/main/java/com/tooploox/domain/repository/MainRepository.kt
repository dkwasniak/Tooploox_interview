package com.tooploox.domain.repository

import com.tooploox.domainmodule.SongsDomainModel
import io.reactivex.Observable

interface MainRepository {

    fun fetchSongs(isiTunesSelected: Boolean, localSelected: Boolean): Observable<SongsDomainModel>
}