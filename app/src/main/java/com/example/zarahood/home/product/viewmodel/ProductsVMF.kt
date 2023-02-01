package com.example.zarahood.home.product.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.zarahood.home.product.model.ProductsC

@Suppress("UNCHECKED_CAST")
class ProductsVMF(
    private val repo: ProductsC.Repository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ) = ProductsViewModel(repo = repo) as T

}
