package com.example.beginnerapp.model

import com.example.beginnerapp.room.entity.ProductEntity

data class Product(
    val id:Long,
    val name:String,
    val description:String,
    val price:Double,
    val imageUrl:String?,
    val rating:Float,
    val isInStock:Boolean
)

//Extension function for mapping between domain and database models
fun Product.toEntity()= ProductEntity(
    id=id,
    name=name,
    description=description,
    price=price,
    imageUrl=imageUrl,
    rating=rating,
    isInStock=isInStock
)
