package com.example.beginnerapp.network.service

import com.example.beginnerapp.network.Constants
import com.example.beginnerapp.network.dto.ProductResponse
import retrofit2.http.GET

interface ApiService {
    @GET(Constants.PRODUCTS_URL)
    suspend fun fetchProducts(): List<ProductResponse>
}