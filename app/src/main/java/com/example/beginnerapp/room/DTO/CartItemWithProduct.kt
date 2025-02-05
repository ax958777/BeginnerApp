package com.example.beginnerapp.room.DTO

import androidx.room.Embedded
import androidx.room.Relation
import com.example.beginnerapp.model.CartItem
import com.example.beginnerapp.room.entity.CartItemEntity
import com.example.beginnerapp.room.entity.ProductEntity
import com.example.beginnerapp.room.entity.toDomain

data class CartItemWithProduct (
    @Embedded val cartItem: CartItemEntity,
    @Relation(
        parentColumn = "productId",
        entityColumn = "id"
    )
    val product: ProductEntity
)

// extension mapping from dto to domain
fun CartItemWithProduct.toDomain():CartItem=CartItem(
    product = product.toDomain(),
    quantity = cartItem.quantity
)