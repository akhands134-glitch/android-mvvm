package com.learndroid.jetpackcompose.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.learndroid.jetpackcompose.presentation.components.ProfileCard
import kotlinx.coroutines.launch

@Composable
fun UserScreen(viewModel: UserViewModel = viewModel()) {
    val users by viewModel.users.collectAsState()
    val listState = rememberLazyListState()

    val scope = rememberCoroutineScope()
    var name by rememberSaveable { mutableStateOf("") }
    var age by remember { mutableStateOf("") }

    LaunchedEffect(viewModel) {
        viewModel.loadUsers()
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text("Sync API to Database", style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(bottom = 16.dp, top = 16.dp),
                color = MaterialTheme.colorScheme.primary,
                )
            Button(
                onClick = {
                    scope.launch {
                        viewModel.fetchNextTodo()
                    } },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Fetch & Save Next API Task",
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 3,
                    modifier = Modifier.padding(top =2.dp, bottom = 3.dp)
                    )

            }

            Spacer(modifier = Modifier.height(24.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(16.dp))

            Text("Add Manual User", style = MaterialTheme.typography.titleMedium)
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = age,
                onValueChange = { age = it },
                label = { Text("Age") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    if (name.isNotBlank() && age.isNotBlank()) {
                        viewModel.addUser(name, age.toIntOrNull() ?: 0)
                        name = ""
                        age = ""
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save to Local DB")
            }

            Spacer(modifier = Modifier.height(24.dp))
            Text("User List (Single Source of Truth)", style = MaterialTheme.typography.headlineSmall)
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            LazyColumn(state = listState,
                modifier = Modifier.weight(1f)
                .padding(start = 10.dp, end = 2.dp)) {


                items(items = users.users,
                    key = { user -> user.id }) { user ->

                    ProfileCard(name = user.name, age = user.age)
                    HorizontalDivider(color = Color.LightGray, thickness = 0.5.dp)
                }
            }
        }
    }
}

@Preview
@Composable
private fun UserScreenPreview() {
    UserScreen()
    MaterialTheme {
        UserScreen()
    }
}
