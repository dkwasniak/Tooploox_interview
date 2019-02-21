package com.tooploox

import com.tooploox.domain.di.SchedulersProvider
import io.reactivex.SingleTransformer


fun <T> applySchedulers(schedulersProvider: SchedulersProvider): SingleTransformer<T, T> {
    return SingleTransformer {
        it.subscribeOn(schedulersProvider.computationScheduler())
            .observeOn(schedulersProvider.mainThreadScheduler())
    }
}