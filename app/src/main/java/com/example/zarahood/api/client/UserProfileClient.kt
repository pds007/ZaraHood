package com.example.zarahood.api.client

import com.example.zarahood.api.models.UserProfileDTO
import com.example.zarahood.utils.ApparelStoreConstants.PROFILE_URL
import retrofit2.http.GET

interface UserProfileClient {

    companion object {
        private const val PROFILE_PATH = "aaf97364-eedc-46a5-8f9e-56eb4b3cedd2"
    }

    @GET("$PROFILE_URL$PROFILE_PATH")
    suspend fun getUserDetails(): UserProfileDTO
}