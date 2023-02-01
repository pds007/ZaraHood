package com.example.zarahood.api.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ProductListResponse(
    @SerializedName("data")
    val dataWrapper: ProductListDataWrapper
)
