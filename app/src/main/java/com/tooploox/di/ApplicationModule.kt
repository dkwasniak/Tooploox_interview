package com.tooploox.di

import com.tooploox.domain.di.SchedulersProvider
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Singleton
    @Provides
    fun provideSchedulers(): SchedulersProvider {
        return object : SchedulersProvider {
            override fun computationScheduler(): Scheduler {
                return Schedulers.computation()
            }

            override fun mainThreadScheduler(): Scheduler {
                return AndroidSchedulers.mainThread()
            }
        }
    }

}