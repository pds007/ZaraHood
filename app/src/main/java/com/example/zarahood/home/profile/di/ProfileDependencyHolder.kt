package com.example.zarahood.home.profile.di

import com.example.zarahood.core.ZaraHoodAppClass


object ProfileDependencyHolder {
    private var profileComponent: ProfileComponent? = null

    fun initProfileComponent(): ProfileComponent {
        if (profileComponent == null) {
            profileComponent = DaggerProfileComponent.builder().apparelStoreComponent(
                ZaraHoodAppClass.apparelStoreComponent
            ).build()
        }
        return profileComponent as ProfileComponent
    }

    fun destroyProfileComponent() {
        profileComponent = null
    }
}