package com.example.beginnerapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.material3.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.beginnerapp.component.ProductCard
import com.example.beginnerapp.viewmodels.ProductsViewModel
import com.example.beginnerapp.viewmodels.UiState

@Composable
fun ProductsScreen(navController:NavHostController, vm:ProductsViewModel=hiltViewModel()){
    val uiState by vm.uiState.collectAsState()
    val products by vm.products.collectAsState()
    Column(
        modifier=Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment =Alignment.CenterHorizontally
    ){
        Text(
            "Products",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )
        when (val state=uiState){
            is UiState.Initial->{
                println("Initial")
            }
            is UiState.Loading->{
                CircularProgressIndicator(modifier = Modifier.size(50.dp))
            }
            is UiState.Success ->{
                LazyColumn {
                    items(products?: emptyList()){
                        product->
                        ProductCard(
                            product = product,
                            onAddToCart = vm::onAddToCart
                        )
                    }
                }
            }
            is UiState.Error->{
                Text(
                    "Error:${state.message}",
                    color= Color.Red,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}