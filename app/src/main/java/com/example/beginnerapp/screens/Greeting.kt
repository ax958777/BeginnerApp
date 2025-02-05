package com.example.beginnerapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.beginnerapp.navigation.Routes
import kotlinx.serialization.Serializable


@Composable
    fun Greeting( navController: NavHostController) {
        Column(
            Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Hello World",
                modifier = Modifier
            )
            Button(onClick = {
                println("Button Clicked")
                navController.navigate(Routes.Login.routes)
            }){
                Text(
                    text = "Login"
                )
            }
            Button(onClick = {
                navController.navigate(Routes.ProductLists.routes)
            }){
                Text(
                    text = "Products"
                )
            }
            Button(onClick = {
                navController.navigate(Routes.ShoppingCart.routes)
            }){
                Text(
                    text = "ShoppingCart"
                )
            }
        }

    }
