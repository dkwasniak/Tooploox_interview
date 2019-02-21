package com.tooploox.domain.viewmodel

import com.tooploox.domainmodule.Source

class SongViewModel(

        val title: String,

        val artistName: String,

        val releaseData: String,

        val source: Source,

        val coverImageUrl: String?
)