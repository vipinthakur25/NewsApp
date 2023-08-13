package com.example.newsapp.domain.repository

import com.example.newsapp.data.remote.dto.Article
import com.example.newsapp.utill.UIState
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getNews(): Flow<UIState<List<Article>>>
}