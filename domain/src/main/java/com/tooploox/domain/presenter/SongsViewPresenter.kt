package com.tooploox.domain.presenter

import com.tooploox.applySchedulers
import com.tooploox.domain.di.SchedulersProvider
import com.tooploox.domain.interactor.MainInteractor
import com.tooploox.domain.view.SongsView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class SongsViewPresenter @Inject constructor(
        private val mainInteractor: MainInteractor,
        private val schedulersProvider: SchedulersProvider

) : BasePresenter<SongsView>() {

    private val subscriptions: CompositeDisposable = CompositeDisposable()

    override fun detach() {
        super.detach()
        subscriptions.clear()
    }

    fun fetchSongs(listOfSources: List<SourceType>) {
        view?.showProgress(true)
        subscriptions.add(mainInteractor.fetchSongs(listOfSources)
                .compose(applySchedulers(schedulersProvider))
                .subscribeBy(
                        onSuccess = {
                            view?.showSongs(it.songs)
                            view?.showProgress(false)
                        },
                        onError = {
                            view?.showError()
                            view?.showProgress(false)
                        }
                ))
    }
}