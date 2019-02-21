package com.tooploox.data.api.model

import com.google.gson.annotations.SerializedName

data class SongApiModel (

    @SerializedName("trackId")
    var trackId: Int,

    @SerializedName("artistName")
    var artistName: String,

    @SerializedName("trackName")
    var trackName: String,

    @SerializedName("artworkUrl100")
    var coverImageUrl: String,

    @SerializedName("releaseDate")
    var releaseData: String

)