package com.tooploox.domain.api.model.autocomplete

import com.google.gson.annotations.SerializedName

data class SuggestionApiModel(

    @SerializedName("term")
    var term: String? = null,

    @SerializedName("count")
    var count: Int? = null,

    @SerializedName("category_name")
    var categoryName: String? = null,

    @SerializedName("category_id")
    var categoryId: Int? = null,

    @SerializedName("type")
    var type: String? = null

)