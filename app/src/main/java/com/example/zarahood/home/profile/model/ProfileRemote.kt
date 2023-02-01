package com.example.zarahood.home.profile.model

import com.example.zarahood.api.client.UserProfileClient


class ProfileRemote(
    private val webService:UserProfileClient
):  ProfileC.Remote{
    
    override suspend fun userProfileApiRequest() =
        webService.getUserDetails()

}