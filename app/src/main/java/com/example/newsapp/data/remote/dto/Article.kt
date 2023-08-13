package com.example.newsapp.data.remote.dto

import android.accounts.AuthenticatorDescription

data class Article(
    val content: String,
    val description: String,
    val publishedAt: String,
    val title: String,
    val urlToImage: String
)
