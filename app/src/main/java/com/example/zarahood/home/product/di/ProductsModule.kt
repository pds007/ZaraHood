package com.example.zarahood.home.product.di

import com.example.zarahood.api.client.ProductsClient
import com.example.zarahood.home.product.model.ProductsC
import com.example.zarahood.home.product.model.ProductsRemote
import com.example.zarahood.home.product.model.ProductsRepo
import com.example.zarahood.home.product.viewmodel.ProductsVMF
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ProductsModule {
    @Provides
    @ProductsScope
    fun providesVMF(
        repo: ProductsC.Repository,
    ) = ProductsVMF(repo = repo)

    @Provides
    @ProductsScope
    fun providesRepo(
        remote: ProductsC.Remote
    ): ProductsC.Repository = ProductsRepo(
        remote = remote,
    )

    @Provides
    @ProductsScope
    fun providesWs(retrofit: Retrofit): ProductsClient =
        retrofit.create(ProductsClient::class.java)

    @Provides
    @ProductsScope
    fun providesRDS(
        repositoryWebService: ProductsClient
    ): ProductsC.Remote = ProductsRemote(
        webService = repositoryWebService
    )
}