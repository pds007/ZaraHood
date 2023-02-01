package com.example.zarahood.home.product.di

import com.example.zarahood.core.ZaraHoodAppClass

object ProductsDependencyHolder {
    private var productsComponent: ProductComponent? = null

    fun initProductsComponent(): ProductComponent {
        if (productsComponent == null) {
            productsComponent = DaggerProductComponent.builder().apparelStoreComponent(
                ZaraHoodAppClass.apparelStoreComponent
            ).build()
        }
        return productsComponent as ProductComponent
    }

    fun destroyProductsComponent() {
        productsComponent = null
    }
}