package com.tooploox.domain.presenter

import com.tooploox.domain.di.SchedulersProvider
import com.tooploox.domain.interactor.MainInteractor
import com.tooploox.domain.view.SongsView
import java.util.*
import javax.inject.Inject

class SongsViewPresenter @Inject constructor(
    private val mainInteractor: MainInteractor,
    private val schedulersProvider: SchedulersProvider

) : BasePresenter<SongsView>() {


    fun fetchSongs(isiTunesSelected: Boolean, isLocalSelected: Boolean) {
        mainInteractor.fetchSongs(isiTunesSelected, isLocalSelected)
    }
   /* fun subscribeForQueryChanges(onTextChangeSubscription: Observable<String>) {
        onTextChangeSubscription
            .applySchedulers(schedulersProvider)
            .subscribeBy(
                onNext = {
                    if (it.isEmpty()) {
                        view?.hideSuggestionView()
                    } else {
                       // getAutoComplete(it)
                    }
                },
                onError = {
                    view?.showError()
                }
            )
    }*/

}