package com.example.newsapp.data.remote.dto

import android.accounts.AuthenticatorDescription
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Article")
data class Article(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val content: String,
    @ColumnInfo(defaultValue = "Default description") val description: String,
    val publishedAt: String,
    val title: String,
    val urlToImage: String
)
