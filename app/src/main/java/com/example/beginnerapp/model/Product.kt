package com.example.beginnerapp.model

data class Product(
    val id:Int,
    val name:String,
    val description:String,
    val price:Double,
    val imageUrl:String?,
    val rating:Float,
    val isInStock:Boolean
)
