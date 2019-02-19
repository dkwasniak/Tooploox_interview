package com.tooploox.data.di

import com.tooploox.data.api.MainApiService
import com.tooploox.data.repository.MainRepositoryImpl
import com.tooploox.domain.repository.MainRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun provideMainRepository(mainApiService: MainApiService): MainRepository {
        return MainRepositoryImpl(mainApiService)
    }
}