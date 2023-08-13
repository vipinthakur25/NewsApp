package com.example.newsapp.data.remote.datasource

import com.example.newsapp.data.remote.api.ApiService
import com.example.newsapp.data.remote.dto.Article
import com.example.newsapp.utill.API_KEY
import com.example.newsapp.utill.COUNTRY_NAME
import javax.inject.Inject


class RemoteDataSourceImp @Inject constructor(private val apiService: ApiService) : RemoteDataSource {
    override suspend fun getNews(): List<Article> {
        val response = apiService.getNews(COUNTRY_NAME, API_KEY)
        return if (response.isSuccessful) {
            val article = response.body()?.articles ?: emptyList()
            article
        } else {
            emptyList()
        }
    }
}