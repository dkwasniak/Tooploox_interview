package com.tooploox.di

import com.tooploox.activity.MainActivity
import com.tooploox.activity.SongsActivity
import com.tooploox.data.di.ApiModule
import com.tooploox.data.di.DataModule
import com.tooploox.domain.di.DomainModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, DomainModule::class, ApiModule::class, DataModule::class])
interface ApplicationComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(songsActivity: SongsActivity)
}