package com.example.zarahood.login.di

import com.example.zarahood.core.ZaraHoodAppClass

object LoginDependencyHolder {

    private var loginComponent: LoginComponent? = null

    fun initLoginComponent(): LoginComponent {
        if (loginComponent == null) {
           loginComponent=DaggerLoginComponent.builder().apparelStoreComponent(
               ZaraHoodAppClass.apparelStoreComponent
           ).build()
        }
        return loginComponent as LoginComponent
    }

    fun destroyLoginComponent() {
        loginComponent = null
    }
}