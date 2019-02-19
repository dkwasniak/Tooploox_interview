package com.tooploox.domain.di

import io.reactivex.Scheduler

interface SchedulersProvider {

    fun computationScheduler(): Scheduler

    fun mainThreadScheduler(): Scheduler
}