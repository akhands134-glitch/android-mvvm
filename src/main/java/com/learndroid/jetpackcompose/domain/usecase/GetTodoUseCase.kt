package com.learndroid.jetpackcompose.domain.usecase

import com.learndroid.jetpackcompose.domain.model.Todo
import com.learndroid.jetpackcompose.domain.repository.TodoRepository
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject

class GetTodoUseCase @Inject constructor(
    private val repository: TodoRepository
) {
    private val mutex = Mutex()
    suspend operator fun invoke(id: Int): Todo {
        mutex.withLock {
            return   repository.getTodo(id)
        }

    }
}
