package com.tooploox.data.api

import com.tooploox.data.api.model.SongsApiModel
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface MainApiService {

    @GET("search?term=rock&country=pl&media=music")
    fun fetchSongs(): Single<Response<SongsApiModel>>
}