package com.tooploox.data.service

import com.tooploox.data.db.model.SongsDbModel
import io.reactivex.Single

interface LocalSongsService {

    fun fetchSongs(): Single<SongsDbModel>
}