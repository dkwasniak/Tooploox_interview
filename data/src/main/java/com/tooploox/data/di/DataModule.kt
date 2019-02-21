package com.tooploox.data.di

import android.content.Context
import com.google.gson.Gson
import com.tooploox.data.api.MainApiService
import com.tooploox.data.db.LocalSongsServiceImpl
import com.tooploox.data.mapper.SongsDataModelsToDomainModelMapper
import com.tooploox.data.repository.MainRepositoryImpl
import com.tooploox.data.service.LocalSongsService
import com.tooploox.domain.repository.MainRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        mainApiService: MainApiService,
        localSongsService: LocalSongsService,
        songsDataModelsToDomainModelMapper: SongsDataModelsToDomainModelMapper
    ): MainRepository {
        return MainRepositoryImpl(mainApiService, localSongsService, songsDataModelsToDomainModelMapper)
    }

    @Singleton
    @Provides
    fun provideLocalSongsService(context: Context, gson: Gson): LocalSongsService {
        return LocalSongsServiceImpl(context, gson)
    }
}