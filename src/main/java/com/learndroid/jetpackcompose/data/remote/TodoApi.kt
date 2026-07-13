package com.learndroid.jetpackcompose.data.remote

import com.learndroid.jetpackcompose.domain.model.Todo
import retrofit2.http.GET
import retrofit2.http.Path

interface TodoApi {
    @GET("todos/{id}")
    suspend fun getTodo(@Path("id") id: Int): Todo
}
