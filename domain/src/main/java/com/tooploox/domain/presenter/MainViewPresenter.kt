package com.tooploox.domain.presenter

import com.tooploox.domain.view.MainView
import javax.inject.Inject

class MainViewPresenter @Inject constructor() : BasePresenter<MainView>() {

    private var isiTunesSelected = false

    private var isLocalSelected = false


    fun onLocalSourceSelect(isSelected: Boolean) {
        isLocalSelected = isSelected
    }

    fun oniTunesSourceSelect(isSelected: Boolean) {
        isiTunesSelected = isSelected
    }

    fun onNextButtonClicked() {
        if (isiTunesSelected || isLocalSelected) {
            view?.goToSongsList(isiTunesSelected, isLocalSelected)
        } else {
            view?.showMissingSelectionInfo()
        }
    }

}