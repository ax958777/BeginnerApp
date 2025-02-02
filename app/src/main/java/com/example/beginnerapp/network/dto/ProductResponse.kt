package com.example.beginnerapp.network.dto

import com.example.beginnerapp.model.Product
import com.example.beginnerapp.network.Constants

data class ProductResponse(
    val id:Int,
    val name:String,
    val description:String,
    val price:Double,
    val images:List<String>,
    val isFeatured:Boolean
)
fun ProductResponse.toProductModel()= Product(
    id=id,
    name=name,
    description=description,
    price=price,
    imageUrl = Constants.BASE_URL+ images[0],
    rating=5f,
    isInStock = true

)
