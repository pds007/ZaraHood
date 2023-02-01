package com.example.zarahood.api.client

import com.example.zarahood.api.models.ProductListResponse
import retrofit2.http.GET

interface ProductsClient {

    companion object {
        private const val PRODUCT_PATH = "bc09a745-4346-4025-9611-9da76366dbbc/"
    }


    @GET(value = PRODUCT_PATH)
    suspend fun getProductList(): ProductListResponse
}