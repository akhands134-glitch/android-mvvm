package com.learndroid.jetpackcompose.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class Greetings {

    @Composable
    fun Greeting(name: String, modifier: Modifier, onClicked: () -> Unit = {}) {

        val expanded = rememberSaveable { mutableStateOf(false) }

        val extraPadding = if(expanded.value) 48.dp else 0.dp

        Surface(color = MaterialTheme.colorScheme.primary){
         Row(modifier = Modifier.padding(24.dp)) {
         Column(modifier = Modifier.weight(1f).padding(bottom = extraPadding)) {
            Text(
            text = "Hello "
             )
        Text(
            text = name
        )

    }
             Button(
                 onClick = onClicked,
             )
             {
                 Text(if(expanded.value) "show less  " else "show more")
             }
}
        }
    }

    @Preview
    @Composable
    fun GreetingPreview() {
        Greeting(name = "Android", modifier = Modifier)
    }

}