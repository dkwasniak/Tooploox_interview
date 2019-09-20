package com.tooploox.domain.repository

import com.tooploox.domain.presenter.SourceType
import com.tooploox.domainmodule.SongsDomainModel
import io.reactivex.Single

interface MainRepository {

    fun fetchSongs(listOfSources: List<SourceType>): Single<SongsDomainModel>
}