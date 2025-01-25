package com.example.beginnerapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.beginnerapp.network.dto.ProductResponse
import com.example.beginnerapp.network.service.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel@Inject constructor(
    private val http:ApiService,
):ViewModel() {
    private val _uiState= MutableStateFlow<UiState>(UiState.Initial)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()
    private val _products= MutableStateFlow<List<ProductResponse>>(emptyList())
    val products: StateFlow<List<ProductResponse>> = _products.asStateFlow()

    init{
        fetchProducts()
    }

    fun fetchProducts(){
        viewModelScope.launch {
            try{
                _uiState.value=UiState.Loading
                val response=http.fetchProducts()
                println("DEBUG-THE ProductsAPIResponse:${response}")
                _uiState.value=UiState.Success
                _products.value=response

            } catch(e:Exception){
                _uiState.value=UiState.Error(e.message?:"An unknown error occurred")

            }
        }
    }
}
sealed class UiState{
    object Initial:UiState()
    object Loading:UiState()
    object Success:UiState()
    data class Error(val message:String):UiState()
}