package com.learndroid.jetpackcompose.domain.repository

import com.learndroid.jetpackcompose.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUsers(): Flow<List<User>>
    suspend fun insertUser(user: User)
}
