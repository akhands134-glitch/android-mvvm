package com.learndroid.jetpackcompose.domain.model

data class Todo(
    val userId: Int,
    val id: Int,
    val title: String,
    val completed: Boolean
)
