package com.example.newsapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.remote.dto.Article
import com.example.newsapp.domain.usecases.NewsCaseImp
import com.example.newsapp.utill.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsArticleViewModel @Inject constructor(private val newsCaseImp: NewsCaseImp) : ViewModel() {

    private var _postData = MutableStateFlow<UIState<List<Article>>>(UIState.Loading)
    val postData: StateFlow<UIState<List<Article>>>
        get() = _postData

    init {
        getPost()
    }

    private fun getPost() {
        viewModelScope.launch {
            try {
                var article = newsCaseImp.invokeGetArticle()
                _postData.emitAll(article)
            } catch (e: Exception) {
                _postData.value = UIState.Failure(e.message ?: "Some thing went wrong")
            }
        }
    }
}