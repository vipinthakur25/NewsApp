package com.example.newsapp.di

import com.example.newsapp.BuildConfig
import com.example.newsapp.data.remote.api.ApiService
import com.example.newsapp.utill.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
class NewsAppModule {
    @Provides
    @Singleton
    fun provideOkHttpclient(): OkHttpClient {
        if (BuildConfig.DEBUG) {
            val httpLoggingInterceptor =
                HttpLoggingInterceptor().setLevel((HttpLoggingInterceptor.Level.BASIC))
            return OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor)
                .build()
        } else {
            return OkHttpClient.Builder().build()
        }
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): ApiService {
        return Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build().create()
    }
}