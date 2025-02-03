package com.example.beginnerapp.room.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.beginnerapp.room.DTO.CartItemWithProduct
import com.example.beginnerapp.room.entity.CartItemEntity
import com.example.beginnerapp.room.entity.ProductEntity

@Dao
interface CartItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartItem(cartItem: CartItemEntity)

    @Query("SELECT * FROM cart_items WHERE productid = :productId")
    suspend fun getCartItemByProductId(productId:Long): CartItemEntity?

    @Query("UPDATE cart_items SET quantity = :quantity WHERE productid = :productId")
    suspend fun updateQuantity(productId:Long,quantity:Int)

    @Query("DELETE FROM cart_items  WHERE productid = :productId")
    suspend fun deleteCartItem(productId: Long)

    @Query("DELETE FROM cart_items")
    suspend fun clearCart()

    @Transaction
    @Query("SELECT * FROM cart_items")
    fun getCartItemsWithProducts(): List<CartItemWithProduct>

}