package com.example.newsapp.utill

import android.content.Context
import android.widget.Toast

const val APIKEY = "apiKey"
const val COUNTRY = "country"
const val COUNTRY_NAME = "us"
const val SOMETHING_WENT_WRONG = "Something went wrong"

const val API_KEY = "a0e2d4a8b37240c4b94a805a62aaf5bf"
const val BASE_URL = "https://newsapi.org/v2/"


fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}