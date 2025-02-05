package com.example.beginnerapp.screens

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.beginnerapp.component.CartSummary
import com.example.beginnerapp.component.ShoppingCart
import com.example.beginnerapp.viewmodels.ShoppingCartViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingCartScreen (
    onBackClick: () -> Unit,
    onCheckout: () -> Unit,
    modifier:Modifier=Modifier,
    viewModel: ShoppingCartViewModel = hiltViewModel()
){
    val uiState by viewModel.uiState.collectAsState()
    Scaffold(
        topBar={
            TopAppBar(
                title = {Text("Shopping Cart")},
                navigationIcon = {
                    IconButton( onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack,"Back")
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = modifier
                //.padding(padding)
                .fillMaxSize(),
        ) {


            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
            ) {
                ShoppingCart(
                    cartItems = uiState.items,
                    onUpdateQuantity = { product, quantity ->
                        viewModel.updateQuantity(product.id, quantity)
                    },
                    onRemoveItem = { product ->
                        viewModel.removeFromCart(product.id)
                    },
                    onCheckout = onCheckout,

                )




            }
            if (uiState.isLoading) {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }
            uiState.error?.let { error ->
                Snackbar(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(error)
                }
            }

        }

    }
}