package com.example.beginnerapp.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes(val routes:String){
    @Serializable
    object Greeting : Routes("greeting")
    @Serializable
    object Login : Routes("login")
    @Serializable
    object ProductLists : Routes("product_lists")
    @Serializable
    object ShoppingCart : Routes("shopping_cart")
}
