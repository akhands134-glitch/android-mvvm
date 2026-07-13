package com.learndroid.jetpackcompose.domain.usecase

import com.learndroid.jetpackcompose.domain.model.User
import com.learndroid.jetpackcompose.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(): Flow<List<User>> {
        return repository.getUsers()
    }
}
