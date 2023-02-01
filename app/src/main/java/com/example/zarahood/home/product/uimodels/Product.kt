package com.example.zarahood.home.product.uimodels

import androidx.recyclerview.widget.DiffUtil
import com.example.zarahood.api.models.ProductDTO

data class Product(
    val brand: String,
    val id: String,
    val name: String,
    val offerPrice: String,
    val price: String,
    val productDesc: String,
    val productUrl: String,
) {
    constructor(remoteModel: ProductDTO) : this(
        brand = remoteModel.brand,
        id = remoteModel.id,
        name = remoteModel.name,
        offerPrice = remoteModel.offerPrice,
        price = remoteModel.price,
        productDesc = remoteModel.productDesc,
        productUrl = remoteModel.productUrl,
    )

    companion object {
        val DIFF_UTIL = object: DiffUtil.ItemCallback<Product>() {
            override fun areContentsTheSame(
                oldItem: Product,
                newItem: Product
            ) = oldItem == newItem

            override fun areItemsTheSame(
                oldItem: Product,
                newItem: Product
            ) = oldItem.id == newItem.id

        }
    }
}