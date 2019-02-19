package com.tooploox.domain.presenter

import com.tooploox.domain.di.SchedulersProvider
import com.tooploox.domain.view.BaseView
import io.reactivex.Observable

open class BasePresenter<T : BaseView> {

    var view: T? = null

    open fun attach(view: T) {
        this.view = view
    }

    open fun detach() {
        this.view = null
    }
}

fun <T : Any> Observable<T>.applySchedulers(schedulersProvider: SchedulersProvider?): Observable<T> {
    return if (schedulersProvider == null) {
        this
    } else {
        this.subscribeOn(schedulersProvider.computationScheduler())
            .observeOn(schedulersProvider.mainThreadScheduler())
    }
}