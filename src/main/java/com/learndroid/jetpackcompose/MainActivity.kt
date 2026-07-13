package com.learndroid.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.learndroid.jetpackcompose.presentation.Greetings
import com.learndroid.jetpackcompose.presentation.Logger
import com.learndroid.jetpackcompose.presentation.UserScreen
import com.learndroid.jetpackcompose.ui.theme.JetpackComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeTheme {
                MyApp(modifier = Modifier.fillMaxSize())
                Logger.log("onCreate")
                //UserScreen()
               // Greetings().Greeting("Android", Modifier)
            }
        }
    }

    @Composable
    fun MyApp(modifier: Modifier){
        var shouldShowGreetings by remember { mutableStateOf(true) }

        Surface(modifier) {
            if(shouldShowGreetings){
                Greetings().Greeting("Android", Modifier, onClicked = {shouldShowGreetings = false})
            }
            else{
                UserScreen()
            }
        }
    }


}
