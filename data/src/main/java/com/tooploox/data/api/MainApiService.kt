package com.tooploox.data.api

import com.tooploox.data.api.model.SongsApiModel
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET

interface MainApiService {

    @GET("search?term=rock&country=pl&media=music")
    fun fetchSongs(): Observable<Response<SongsApiModel>>
}