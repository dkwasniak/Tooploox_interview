package com.tooploox.domain.presenter

import com.tooploox.domain.view.MainView
import javax.inject.Inject

class MainViewPresenter @Inject constructor() : BasePresenter<MainView>() {

    private var isiTunesSelected = false

    private var isLocalSelected = false

    private var isThirdSelected = false

    private val sourcesList = mutableListOf<SourceType>()


    fun onLocalSourceSelect(isSelected: Boolean) {
        isLocalSelected = isSelected
    }

    fun oniTunesSourceSelect(isSelected: Boolean) {
        isiTunesSelected = isSelected
    }

    fun onThirdSourceSelected(isSelected: Boolean) {
        isThirdSelected = isSelected
    }

    fun onNextButtonClicked() {
        if (isiTunesSelected || isLocalSelected) {
            view?.goToSongsList(isiTunesSelected, isLocalSelected, isThirdSelected)
        } else {
            view?.showMissingSelectionInfo()
        }
    }
}

enum class SourceType {
    ITUNES,
    LOCAL,
    THIRD
}