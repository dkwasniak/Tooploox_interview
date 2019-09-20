package com.tooploox.domain.view


interface MainView : BaseView {

    fun showMissingSelectionInfo()

    fun goToSongsList(isiTunesSelected: Boolean, localSelected: Boolean, thirdSelected: Boolean)


}