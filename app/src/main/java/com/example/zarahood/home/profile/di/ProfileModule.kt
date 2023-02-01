package com.example.zarahood.home.profile.di

import com.example.zarahood.api.client.UserProfileClient
import com.example.zarahood.home.profile.model.ProfileC
import com.example.zarahood.home.profile.model.ProfileRemote
import com.example.zarahood.home.profile.model.ProfileRepository
import com.example.zarahood.home.profile.viewmodel.ProfileVMF
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ProfileModule {
    @Provides
    @ProfileScope
    fun providesVMF(
        repo: ProfileC.Repository,
    ) = ProfileVMF(repo = repo)

    @Provides
    @ProfileScope
    fun providesRepo(
        remote: ProfileC.Remote
    ): ProfileC.Repository = ProfileRepository(
        remote = remote,
    )

    @Provides
    @ProfileScope
    fun providesWs(retrofit: Retrofit): UserProfileClient =
        retrofit.create(UserProfileClient::class.java)

    @Provides
    @ProfileScope
    fun providesRDS(
        repositoryWebService: UserProfileClient
    ): ProfileC.Remote = ProfileRemote(
        webService = repositoryWebService
    )
}