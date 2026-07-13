package com.learndroid.jetpackcompose.presentation

sealed class Result {

    data class Success(val data: String) : Result()
    data class Error(val exception: Exception) : Result()
    object Loading : Result()

}