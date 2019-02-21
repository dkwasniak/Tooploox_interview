package com.tooploox.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.tooploox.domain.di.SchedulersProvider
import com.tooploox.utils.TooplooxImageLoader
import com.tooploox.utils.imageloader.GlideImageLoader
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@Module
class ApplicationModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Singleton
    @Provides
    fun provideContext(): Context {
        return context
    }

    @Singleton
    @Provides
    fun provideImageloader(): TooplooxImageLoader {
        return GlideImageLoader(context)
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