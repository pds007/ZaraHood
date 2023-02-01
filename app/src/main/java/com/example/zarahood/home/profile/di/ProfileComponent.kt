package com.example.zarahood.home.profile.di

import com.example.zarahood.core.di.ApparelStoreComponent
import com.example.zarahood.home.profile.view.ProfileFragment
import dagger.Component

@ProfileScope
@Component(
    dependencies = [ApparelStoreComponent::class],
    modules = [ProfileModule::class]
)
interface ProfileComponent {
    fun inject(fragment: ProfileFragment)
}