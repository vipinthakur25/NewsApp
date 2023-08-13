package com.example.newsapp.di

import android.content.Context
import com.example.newsapp.BuildConfig
import com.example.newsapp.data.local.dao.ArticleDao
import com.example.newsapp.data.local.db.AppDatabase
import com.example.newsapp.data.remote.api.ApiService
import com.example.newsapp.utill.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
            return OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()
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


    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideArticleDao(appDatabase: AppDatabase): ArticleDao {
        return appDatabase.getArticleDao()
    }
}