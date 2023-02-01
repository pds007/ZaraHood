package com.example.zarahood.home.product.model

import com.example.zarahood.api.client.ProductsClient


class ProductsRemote(
    private val webService: ProductsClient
) : ProductsC.Remote {

    override suspend fun getProducts() = webService.getProductList()
        .dataWrapper
        .products

}
