package com.example.beginnerapp.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.beginnerapp.room.DAO.CartItemDao
import com.example.beginnerapp.room.DAO.ProductDao
import com.example.beginnerapp.room.entity.CartItemEntity
import com.example.beginnerapp.room.entity.ProductEntity

@Database(
    entities = [ProductEntity::class,CartItemEntity::class],
    version=1,
    exportSchema = false
)
abstract class ShoppingCartDatabase : RoomDatabase(){
    abstract fun productDao():ProductDao
    abstract fun cartItemDao():CartItemDao
}