package com.example.zarahood.home.product.model

import android.util.Log
import com.example.zarahood.home.product.uimodels.Product
import com.example.zarahood.utils.processingStates.State
import kotlinx.coroutines.flow.MutableStateFlow

class ProductsRepo(
    private val remote: ProductsC.Remote,
) : ProductsC.Repository {

    companion object {
        private const val TAG = "ProductsRepo"
    }

    override val products: MutableStateFlow<State<List<Product>>> = MutableStateFlow(
        State.publishLoading(isLoading = false)
    )

    override suspend fun getProducts() {
        products.emit(value = State.publishLoading(isLoading = true))
        try {
            val response = remote.getProducts()
                val productsSortedOrderList = response.sortedBy {
                    it.name
                }.map(::Product)
            products.emit(State.publishData(data = productsSortedOrderList))
        } catch (
            ex: Throwable
        ) {
            Log.e(TAG,"getProducts",ex)
            products.emit(State.publishError(e = ex))
        }
    }

}