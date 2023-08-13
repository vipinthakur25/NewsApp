package com.example.newsapp.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.newsapp.data.remote.dto.Article
import com.example.newsapp.databinding.ActivityMainBinding
import com.example.newsapp.presentation.adapter.ArticleAdapter
import com.example.newsapp.presentation.viewmodel.NewsArticleViewModel
import com.example.newsapp.utill.UIState
import com.example.newsapp.utill.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: NewsArticleViewModel by viewModels()
    private lateinit var articleAdapter: ArticleAdapter
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setUpUi()
        setContentView(binding.root)
    }

    private fun setUpUi() {

        lifecycleScope.launch {
            viewModel.postData.collectLatest {
                when (it) {
                    is UIState.Success -> {
                        loadUi(it.data)
                    }

                    is UIState.Failure -> {
                        showErrorState(it.message)
                    }

                    is UIState.Loading -> showLoadingState()
                }
            }
        }
    }

    private fun loadUi(data: List<Article>) {
        binding.progressCircular.visibility = View.GONE
        binding.rvArticles.visibility = View.VISIBLE
        articleAdapter = ArticleAdapter()
        articleAdapter.submitList(data)
        binding.rvArticles.apply {
            adapter = articleAdapter
            hasFixedSize()
        }
    }

    private fun showErrorState(errorMessage: String) {
        binding.progressCircular.visibility = View.GONE
        binding.rvArticles.visibility = View.GONE
        showToast(errorMessage)

    }

    private fun showLoadingState() {
        binding.progressCircular.visibility = View.VISIBLE
    }
}