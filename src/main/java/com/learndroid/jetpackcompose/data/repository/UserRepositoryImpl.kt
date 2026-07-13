package com.learndroid.jetpackcompose.data.repository

import com.learndroid.jetpackcompose.data.local.dao.UserDao
import com.learndroid.jetpackcompose.data.local.entity.UserEntity
import com.learndroid.jetpackcompose.domain.model.User
import com.learndroid.jetpackcompose.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {

    override fun getUsers(): Flow<List<User>> {
        return userDao.getAllUsers().map { entities ->
            entities.map { User(it.id, it.name, it.age) }
        }
    }

    override suspend fun insertUser(user: User) {
        userDao.insertUser(UserEntity(name = user.name, age = user.age))
    }
}
