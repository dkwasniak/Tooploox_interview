package com.tooploox.domain.view

import com.tooploox.domain.viewmodel.SongViewModel


interface SongsView : BaseView {

    fun showSongs(songs: List<SongViewModel>)

    fun showProgress(show: Boolean)
}