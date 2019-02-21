package com.tooploox.data.db.model

import com.google.gson.annotations.SerializedName

data class SongsDbModel (

    @SerializedName("list")
    var songs: List<SongDbModel>

)