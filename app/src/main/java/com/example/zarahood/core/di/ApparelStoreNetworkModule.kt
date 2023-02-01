package com.example.zarahood.core.di

import android.content.Context
import android.net.Uri
import android.util.Log
import com.example.zarahood.utils.ApparelStoreConstants.BASE_URL
import com.example.zarahood.utils.ApparelStoreConstants.CONTENT_TYPE_KEY
import com.example.zarahood.utils.ApparelStoreConstants.CONTENT_TYPE_VALUE
import com.example.zarahood.utils.ApparelStoreConstants.NETWORK_REQUEST_TIMEOUT
import com.facebook.stetho.BuildConfig
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ApparelStoreNetworkModule {

    @Provides
    @Singleton
    fun providesRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        @NetworkOkHttp okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
    }


    @Provides
    @Singleton
    fun providesConnectionPool(): ConnectionPool {
        return ConnectionPool()
    }

    @Provides
    @NetworkOkHttp
    @Singleton
    fun providesOkHttpClient(
        connectionPool: ConnectionPool,
        cache: Cache
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .cache(cache)
            .connectionPool(connectionPool)
            .connectTimeout(NETWORK_REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(NETWORK_REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(NETWORK_REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val original: Request = chain.request()
                val request: Request = original.newBuilder()
                    .header(CONTENT_TYPE_KEY, CONTENT_TYPE_VALUE)
                    .method(original.method, original.body)
                    .build()
                chain.proceed(request)
            }
            .addNetworkInterceptor(StethoInterceptor())
            .build()
    }

    @Provides
    @PicassoOkHttp
    @Singleton
    fun providesPicassoOkHttp(connectionPool: ConnectionPool, cache: Cache): OkHttpClient {
        return OkHttpClient.Builder()
            .cache(cache)
            .connectionPool(connectionPool)
            .connectTimeout(NETWORK_REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(NETWORK_REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(NETWORK_REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .addNetworkInterceptor(StethoInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun providesOkhttpCache(context: Context): Cache {
        val cacheSize = 10 * 1024 * 1024 // 10 MB
        return Cache(context.cacheDir, cacheSize.toLong())
    }

    @Provides
    @Singleton
    fun providesGson(): Gson {
        return GsonBuilder()
            .create()
    }

    @Provides
    @Singleton
    fun providesGsonConverterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    @Singleton
    fun providesOkhttp3Downloader(@PicassoOkHttp okHttpClient: OkHttpClient): OkHttp3Downloader {
        return OkHttp3Downloader(okHttpClient)
    }

    @Provides
    @Singleton
    fun providesPicasso(context: Context, okHttp3Downloader: OkHttp3Downloader): Picasso {
        return Picasso.Builder(context)
            .apply {
                if (BuildConfig.DEBUG)
                    listener { _: Picasso?, uri: Uri?, exception: Exception? ->
                        Log.e(
                            "picasso",
                            uri.toString(),
                            exception ?: Exception("unable to trace exception")
                        )
                    }
            }
            .downloader(okHttp3Downloader)
            .build()
    }

}