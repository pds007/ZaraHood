package com.example.zarahood.home.profile.uimodels

import com.example.zarahood.api.models.UserProfileDTO


data class UserProfileUiModel(
    val address: String,
    val dob: String,
    val firstname: String,
    val id: String,
    val lastName: String,
    val pointsEarned: String,
    val username: String,
    val walletBalance: String,
) {
    constructor(remoteModel: UserProfileDTO) : this(
        address = remoteModel.address,
        dob = remoteModel.dob,
        firstname = remoteModel.firstname,
        id = remoteModel.id,
        lastName = remoteModel.lastName,
        pointsEarned = remoteModel.pointsEarned,
        username = remoteModel.username,
        walletBalance = remoteModel.walletBalance
    )
}