package com.example.beginnerapp.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_items")
class CartItemEntity (
    @PrimaryKey(autoGenerate = true)
    val id:Long=0,
    val productId:Long,
    val quantity:Int
)