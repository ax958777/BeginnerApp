package com.example.beginnerapp.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.beginnerapp.model.Product

@Entity(tableName="products")
data class ProductEntity(
    @PrimaryKey
    val id:Long,
    val name:String,
    val description:String,
    val price:Double,
    val imageUrl:String?,
    val rating:Float,
    val isInStock:Boolean
)

//Extension function for mapping between room database to domain
fun ProductEntity.toDoamin()= Product(

  id=id,
 
  name=name,
 
  description=description,
 
  price=price,
 
  imageUrl=imageUrl,
 
  rating=rating,
 
  isInStock=isInStock
)