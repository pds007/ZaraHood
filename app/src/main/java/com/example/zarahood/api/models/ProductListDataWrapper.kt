package com.example.zarahood.api.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ProductListDataWrapper(
    @SerializedName("products")
    val products: List<ProductDTO>
)