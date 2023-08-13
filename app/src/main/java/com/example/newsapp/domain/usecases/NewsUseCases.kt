package com.example.newsapp.domain.usecases

import com.example.newsapp.data.remote.dto.Article
import com.example.newsapp.utill.UIState
import kotlinx.coroutines.flow.Flow

interface NewsUseCases {
    suspend fun invokeGetArticle(): Flow<UIState<List<Article>>>
}