package com.example.zarahood.core.di

import android.content.Context
import com.example.zarahood.core.ApparelStoreModule
import com.example.zarahood.core.ZaraHoodAppClass
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApparelStoreModule::class,
        ApparelStoreNetworkModule::class,
    ]
)
interface ApparelStoreComponent {
    fun context(): Context
    fun inject(app: ZaraHoodAppClass)
    fun retrofit(): Retrofit
}