package com.example.beginnerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.beginnerapp.navigation.NavGraph
import com.example.beginnerapp.screens.Greeting
import com.example.beginnerapp.ui.theme.BeginnerAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        println("Application Started...")
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BeginnerAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {

                    val navController = rememberNavController()
                    val navGraph=NavGraph(navController)

                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BeginnerAppTheme {
        val navController = rememberNavController()
        Greeting(navController)
    }
}