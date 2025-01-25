package com.example.beginnerapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.beginnerapp.screens.Greeting
import com.example.beginnerapp.screens.Login
import com.example.beginnerapp.screens.ProductsScreen

@Composable
fun NavGraph(navController:NavHostController){
    NavHost(navController=navController, startDestination = Routes.Greeting.routes) {
        composable(Routes.Greeting.routes){
            Greeting(navController)
        }
        composable(Routes.Login.routes){
            Login(navController)
        }
        composable(Routes.Products.routes){
            ProductsScreen(navController)
        }

    }
}
