package com.tooploox.data.db.model

import com.google.gson.annotations.SerializedName

data class SongDbModel(

    @SerializedName("Song Clean")
    var songClean: String,

    @SerializedName("ARTIST CLEAN")
    var artistClean: String,

    @SerializedName("Release Year")
    var releaseDate: String

)