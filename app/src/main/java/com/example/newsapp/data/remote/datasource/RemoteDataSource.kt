package com.example.newsapp.data.remote.datasource

import com.example.newsapp.data.remote.dto.Article

interface RemoteDataSource {
    suspend fun getNews(): List<Article>
}