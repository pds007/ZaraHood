package com.example.zarahood.home.product.di

import com.example.zarahood.core.di.ApparelStoreComponent
import com.example.zarahood.home.product.view.ProductsFragment
import dagger.Component

@ProductsScope
@Component(
    dependencies = [ApparelStoreComponent::class],
    modules = [ProductsModule::class]
)
interface ProductComponent {
    fun inject(fragment: ProductsFragment)
}