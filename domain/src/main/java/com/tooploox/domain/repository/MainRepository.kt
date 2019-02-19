package com.tooploox.domain.repository

import com.tooploox.domain.api.model.autocomplete.SuggestionApiModel
import io.reactivex.Observable

interface MainRepository {

    fun getAutoComplete(query: String): Observable<List<SuggestionApiModel>>
}