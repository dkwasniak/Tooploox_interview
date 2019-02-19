package com.tooploox.data.api.model

import com.google.gson.annotations.SerializedName

data class SongsApiModel (

    @SerializedName("resultCount")
    var resultCount: Int? = null,

    @SerializedName("results")
    var results: List<SongApiModel>? = null

)