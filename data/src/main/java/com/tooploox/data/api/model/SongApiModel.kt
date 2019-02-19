package com.tooploox.data.api.model

import com.google.gson.annotations.SerializedName

data class SongApiModel (

    @SerializedName("trackId")
    var trackId: Int? = null,

    @SerializedName("artistName")
    var artistName: String? = null,

    @SerializedName("trackName")
    var trackName: String? = null,

    @SerializedName("releaseDate")
    var releaseDate: String? = null

)