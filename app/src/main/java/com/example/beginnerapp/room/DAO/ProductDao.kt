package com.example.beginnerapp.room.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.beginnerapp.room.entity.ProductEntity

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product:ProductEntity)

    @Query("SELECT * FROM products WHERE id = :productId")
    suspend fun getProduct(productId:Long):ProductEntity?

    @Delete
    suspend fun deleteProduct(product:ProductEntity)
}