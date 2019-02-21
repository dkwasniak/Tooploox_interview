package com.tooploox.domainmodule

data class SongDomainModel(
        val title: String,

        val artistName: String,

        val releaseDate: String,

        val source: Source,

        val coverImageUrl: String? = null
)

enum class Source {
    LOCAL,
    ITUNES
}