package com.tooploox.domain.api.model.autocomplete

import com.google.gson.annotations.SerializedName

data class AutocompleteApiModel(

    @SerializedName("suggestions")
    var suggestions: List<SuggestionApiModel>? = null,

    @SerializedName("category_suggestions")
    var categorySuggestions: Any? = null

)