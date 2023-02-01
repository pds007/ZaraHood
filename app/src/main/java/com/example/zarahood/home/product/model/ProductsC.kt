package com.example.zarahood.home.product.model

import com.example.zarahood.home.product.uimodels.Product
import com.example.zarahood.api.models.ProductDTO
import com.example.zarahood.utils.processingStates.State
import kotlinx.coroutines.flow.Flow

interface ProductsC {
    interface Repository {
        val products: Flow<State<List<Product>>>
        suspend fun getProducts()
    }

    interface Remote {
        suspend fun getProducts(): List<ProductDTO>
    }
}