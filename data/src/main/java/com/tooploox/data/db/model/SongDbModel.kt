package com.tooploox.data.db.model

import com.google.gson.annotations.SerializedName

data class SongDbModel(

    @SerializedName("Song Clean")
    var songClean: String? = null,

    @SerializedName("ARTIST CLEAN")
    var artistClean: String? = null,

    @SerializedName("Release Year")
    var releaseYear: String? = null,

    @SerializedName("COMBINED")
    var combined: String? = null,

    @SerializedName("First?")
    var first: Int? = null,

    @SerializedName("Year?")
    var year: Int? = null,

    @SerializedName("PlayCount")
    var playCount: Int? = null,

    @SerializedName("F*G")
    var fG: Int? = null

)