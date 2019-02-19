package com.tooploox.data.repository

import com.tooploox.data.api.MainApiService
import com.tooploox.domain.api.model.autocomplete.SuggestionApiModel
import com.tooploox.domain.repository.MainRepository
import io.reactivex.Observable

class MainRepositoryImpl(private val mainApiService: MainApiService) : MainRepository {


    override fun getAutoComplete(query: String): Observable<List<SuggestionApiModel>> {
        return mainApiService.getAutoComplete(query).map {
            if (it.isSuccessful) {
                it.body()?.suggestions ?: listOf()
            } else {
                listOf()
            }
        }
    }


}