package com.tooploox.data.api

import com.tooploox.domain.api.model.autocomplete.AutocompleteApiModel
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MainApiService {

    @GET("/search?term=rock&country=pl&media=music")
    fun getAutoComplete(@Query("term") term: String? = "rock"): Observable<Response<AutocompleteApiModel>>
}