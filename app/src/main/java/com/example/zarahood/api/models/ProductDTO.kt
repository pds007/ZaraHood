package com.example.zarahood.api.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ProductDTO(
    @SerializedName(value = "brand")
    val brand: String,
    @SerializedName(value = "id")
    val id: String,
    @SerializedName(value = "name")
    val name: String,
    @SerializedName(value = "offerPrice")
    val offerPrice: String,
    @SerializedName(value = "price")
    val price: String,
    @SerializedName(value = "productDesc")
    val productDesc: String,
    @SerializedName(value = "productUrl")
    val productUrl: String
)
