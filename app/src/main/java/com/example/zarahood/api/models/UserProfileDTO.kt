package com.example.zarahood.api.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class UserProfileDTO(
    @SerializedName(value = "address")
    val address: String,
    @SerializedName(value = "dob")
    val dob: String,
    @SerializedName(value = "firstname")
    val firstname: String,
    @SerializedName(value = "id")
    val id: String,
    @SerializedName(value = "lastName")
    val lastName: String,
    @SerializedName(value = "pointsEarned")
    val pointsEarned: String,
    @SerializedName(value = "username")
    val username: String,
    @SerializedName(value = "walletBalance")
    val walletBalance: String
)