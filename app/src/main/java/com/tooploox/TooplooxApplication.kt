package com.tooploox

import android.app.Application
import com.tooploox.data.di.ApiModule
import com.tooploox.di.ApplicationComponent
import com.tooploox.di.ApplicationModule
import com.tooploox.di.DaggerApplicationComponent

class RicardoApplication : Application() {

    companion object {
        lateinit var instance: RicardoApplication
            private set
    }

    val applicationComponent: ApplicationComponent =
        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule())
            .apiModule(ApiModule(BuildConfig.SERVER_URL)).build()


    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}

val applicationComponent: ApplicationComponent
    get() {
        return RicardoApplication.instance.applicationComponent
    }

