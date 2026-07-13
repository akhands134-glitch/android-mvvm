package com.learndroid.jetpackcompose.domain.usecase

import com.learndroid.jetpackcompose.domain.model.User
import com.learndroid.jetpackcompose.domain.repository.UserRepository
import javax.inject.Inject

class AddUserUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(user: User) {
        repository.insertUser(user)
    }
}
