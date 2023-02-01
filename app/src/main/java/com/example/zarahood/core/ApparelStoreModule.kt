package com.example.zarahood.core

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApparelStoreModule (val context: Context) {

    @Provides
    @Singleton
    fun providesContext(): Context {
        return context
    }
}