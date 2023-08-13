package com.example.newsapp.domain.repository

import com.example.newsapp.data.remote.datasource.RemoteDataSourceImp
import com.example.newsapp.data.remote.dto.Article
import com.example.newsapp.utill.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NewsRepositoryImp @Inject constructor(private val dataSourceImp: RemoteDataSourceImp) :
    NewsRepository {
    override suspend fun getNews(): Flow<UIState<List<Article>>> {
        return flow {
            emit(UIState.Loading)
            try {
                val article = dataSourceImp.getNews()
                emit(UIState.Success(article))
            } catch (e: Exception) {
                emit(UIState.Failure(e.message ?: "Something went wrong"))
            }
        }.flowOn(Dispatchers.IO)
    }
}
