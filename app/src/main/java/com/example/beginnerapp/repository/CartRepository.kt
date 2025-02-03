package com.example.beginnerapp.repository

import com.example.beginnerapp.model.Product
import com.example.beginnerapp.model.toEntity
import com.example.beginnerapp.room.DAO.CartItemDao
import com.example.beginnerapp.room.DAO.ProductDao
import com.example.beginnerapp.room.entity.CartItemEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CartRepository @Inject constructor(
    private val cartItemDao:CartItemDao,
    private val productDao:ProductDao
){
    suspend fun addToCart(product: Product, quantity:Int=1){
        //Ensure products table have an entry of the cartItem-product
        productDao.insertProduct(product.toEntity())

        //Check if the item is in cart
        val existingCartItem=cartItemDao.getCartItemByProductId(product.id)

        if(existingCartItem!=null){
            //Update quantity if item exist
            cartItemDao.updateQuantity(product.id,existingCartItem.quantity+quantity)
        } else{
            // Add new cart item if it doesn't exist
            cartItemDao.insertCartItem(
                CartItemEntity(
                    productId = product.id,
                    quantity = quantity
                )
            )
        }

    }
}