package com.learndroid.jetpackcompose.domain.repository

import com.learndroid.jetpackcompose.domain.model.Todo

interface TodoRepository {
    suspend fun getTodo(id: Int): Todo
}
