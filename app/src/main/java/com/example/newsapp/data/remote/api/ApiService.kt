package com.example.newsapp.data.remote.api

import com.example.newsapp.data.remote.dto.NewsResponse
import com.example.newsapp.utill.APIKEY
import com.example.newsapp.utill.COUNTRY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top-headlines")
    suspend fun getNews(
        @Query(COUNTRY) country : String,
        @Query(APIKEY) apiKey : String
    ) : Response<NewsResponse>
}