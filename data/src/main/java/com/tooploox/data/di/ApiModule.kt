package com.tooploox.data.di

import com.tooploox.data.api.MainApiService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule(private val baseUrl: String) {


    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val okHttpBuilder = OkHttpClient.Builder()
        return okHttpBuilder.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }


    @Singleton
    @Provides
    fun provideMainApiService(retrofit: Retrofit): MainApiService {
        return retrofit.create(MainApiService::class.java)
    }
}