package com.learndroid.jetpackcompose.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learndroid.jetpackcompose.domain.model.User
import com.learndroid.jetpackcompose.domain.model.UserState
import com.learndroid.jetpackcompose.domain.usecase.AddUserUseCase
import com.learndroid.jetpackcompose.domain.usecase.GetTodoUseCase
import com.learndroid.jetpackcompose.domain.usecase.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val addUserUseCase: AddUserUseCase,
    private val getTodoUseCase: GetTodoUseCase
) : ViewModel() {

    private val _uiUserState = MutableStateFlow(UserState())
    val users = _uiUserState.asStateFlow()

   // val list = mutableListOf("A")

    private var currentTodoId = 0

    /*init {
        loadUsers()
    }*/

    fun loadUsers() {
        handledResult(result = Result.Loading)
        viewModelScope.launch {
            // Observing Flow from Local DB
            getUsersUseCase().collect {
                _uiUserState.value = _uiUserState.value.copy(isLoading = false,
                    users = it,
                    error = "")
                handledResult(result = Result.Success("Success"))

            }
            //_users.value.let { list.add("A") }
        }
    }

    private fun handledResult(result: Result) {
        when (result){
            is Result.Success -> {
                Logger.log("Success")
            }
            is Result.Error -> {
                Logger.log("Error")
            }
            is Result.Loading -> {
                Logger.log("Loading")
            }
        }
    }

    fun fetchNextTodo() {
        viewModelScope.launch (Dispatchers.IO) {
            try {
                currentTodoId++
                // 1. API Call
                val fetchedTodo = getTodoUseCase(currentTodoId)

                // 2. Save to Local DB
                // Mapping to User for demonstration
                addUserUseCase(
                    User(
                        id = fetchedTodo.id,
                        name = fetchedTodo.title,
                        age = fetchedTodo.id
                    )
                )
                
                // 3. UI Updates automatically because loadUsers() 
                // is collecting the Flow from the database.
                
            } catch (e: Exception) {
                Logger.log("Error fetching and saving todo$e")
                _uiUserState.value = _uiUserState.value.copy(isLoading = false,
                    error = "Error fetching and saving$e")
                currentTodoId--
            }
        }
    }

    fun addUser(name: String, age: Int) {
        viewModelScope.launch {
            addUserUseCase(User(name = name, age = age))
        }
    }
}
