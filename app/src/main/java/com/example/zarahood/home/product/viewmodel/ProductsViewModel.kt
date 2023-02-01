package com.example.zarahood.home.product.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.zarahood.home.product.di.ProductsDependencyHolder
import com.example.zarahood.home.product.model.ProductsC
import com.example.zarahood.home.product.uimodels.Product
import com.example.zarahood.utils.processingStates.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductsViewModel(
    private val repo: ProductsC.Repository,
) : ViewModel() {

    val repoListLiveData: LiveData<State<List<Product>>> =
        repo.products.asLiveData()

    fun getProducts() {
        viewModelScope.launch(
            context = Dispatchers.IO
        ) {
            repo.getProducts()
        }
    }

    override fun onCleared() {
        super.onCleared()
        ProductsDependencyHolder.destroyProductsComponent()
    }

}