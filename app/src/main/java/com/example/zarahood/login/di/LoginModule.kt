package com.example.zarahood.login.di

import com.example.zarahood.login.model.LoginC
import com.example.zarahood.login.model.LoginRepo
import com.example.zarahood.login.viewModel.LoginVMF
import dagger.Module
import dagger.Provides

@Module
class LoginModule {

    @Provides
    @LoginScreenScope
    fun providesVMF(
        repo: LoginC.Repository,
    ): LoginVMF = LoginVMF(repo = repo)

    @Provides
    @LoginScreenScope
    fun providesRepo(): LoginC.Repository = LoginRepo()
}