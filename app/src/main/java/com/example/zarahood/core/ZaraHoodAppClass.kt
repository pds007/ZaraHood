package com.example.zarahood.core

import android.app.Application
import android.util.Log
import com.example.zarahood.core.di.ApparelStoreComponent
import com.example.zarahood.core.di.DaggerApparelStoreComponent
import com.squareup.picasso.Picasso
import javax.inject.Inject

class ZaraHoodAppClass : Application() {

    companion object {
        private const val TAG = "ZaraHoodAppClass"
        var apparelStoreComponent: ApparelStoreComponent? = null
    }

    @set:Inject
    lateinit var picasso:Picasso

    override fun onCreate() {
        super.onCreate()
        apparelStoreComponent = DaggerApparelStoreComponent.builder()
            .apparelStoreModule(ApparelStoreModule(context = this))
            .build()
        Log.e(TAG,"apparelStoreComponent:${apparelStoreComponent == null}")
        apparelStoreComponent?.inject(this)
        Picasso.setSingletonInstance(picasso)
    }

}