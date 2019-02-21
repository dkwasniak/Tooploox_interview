package com.tooploox.domain.presenter

import com.tooploox.domain.di.SchedulersProvider
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

open class BasePresenterTest {

    var schedulersProvider: SchedulersProvider = object : SchedulersProvider {
        override fun computationScheduler(): Scheduler {
            return Schedulers.computation()
        }

        override fun mainThreadScheduler(): Scheduler {
            return Schedulers.computation()
        }

    }

}