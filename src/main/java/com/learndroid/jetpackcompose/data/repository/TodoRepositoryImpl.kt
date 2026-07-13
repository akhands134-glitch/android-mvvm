package com.learndroid.jetpackcompose.data.repository

import com.learndroid.jetpackcompose.data.remote.TodoApi
import com.learndroid.jetpackcompose.domain.model.Todo
import com.learndroid.jetpackcompose.domain.repository.TodoRepository
import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(
    private val api: TodoApi
) : TodoRepository {
    override suspend fun getTodo(id: Int): Todo {
        return api.getTodo(id)
    }
}
