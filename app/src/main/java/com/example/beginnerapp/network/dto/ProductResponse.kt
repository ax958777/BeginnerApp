package com.example.beginnerapp.network.dto

data class ProductResponse(
    val id:Int,
    val name:String,
    val description:String,
    val price:Float,
    val images:List<String>,
    val isFeatured:Boolean
)
