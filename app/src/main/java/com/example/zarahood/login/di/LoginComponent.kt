package com.example.zarahood.login.di

import com.example.zarahood.core.di.ApparelStoreComponent
import com.example.zarahood.login.view.LoginActivity
import dagger.Component

@LoginScreenScope
@Component(
    dependencies = [ApparelStoreComponent::class],
    modules = [LoginModule::class]
)
interface LoginComponent {
    fun inject(activity: LoginActivity)
}