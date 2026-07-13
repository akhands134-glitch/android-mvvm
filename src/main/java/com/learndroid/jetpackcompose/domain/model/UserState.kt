package com.learndroid.jetpackcompose.domain.model

data class UserState(
    val isLoading : Boolean = false,
    val users : List<User> = emptyList(),
    val error : String = ""
)
