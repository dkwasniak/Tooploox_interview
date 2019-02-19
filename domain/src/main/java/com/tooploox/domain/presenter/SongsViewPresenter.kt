package com.tooploox.domain.presenter

import com.tooploox.domain.di.SchedulersProvider
import com.tooploox.domain.interactor.MainInteractor
import com.tooploox.domain.view.SongsView
import javax.inject.Inject

class SongsViewPresenter @Inject constructor(
    private val mainInteractor: MainInteractor,
    private val schedulersProvider: SchedulersProvider

) : BasePresenter<SongsView>() {


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