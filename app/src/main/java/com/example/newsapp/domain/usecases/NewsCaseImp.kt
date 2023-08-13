package com.example.newsapp.domain.usecases

import com.example.newsapp.data.remote.dto.Article
import com.example.newsapp.domain.repository.NewsRepositoryImp
import com.example.newsapp.utill.UIState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsCaseImp @Inject constructor(private val newsRepositoryImp: NewsRepositoryImp) :
    NewsUseCases {
    override suspend fun invokeGetArticle(): Flow<UIState<List<Article>>> {
        return newsRepositoryImp.getNews()
    }

}