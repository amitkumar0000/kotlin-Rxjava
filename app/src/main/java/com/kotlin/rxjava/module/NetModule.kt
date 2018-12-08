package com.kotlin.rxjava.module

import android.app.Application
import com.google.gson.Gson
import com.kotlin.rxjava.ui.networking.CryptocurrencyService
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetModule(var baseUrl: String) {

    //1. Provide Cache
    @Provides
    @Singleton
    fun ProvidesCache(application: Application): Cache {
        return Cache(application.cacheDir, 10 * 1024 * 1024)
    }

    //2. Provide HttpLoggingInterceptor
    @Provides
    @Singleton
    fun ProvidesLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    //3. Provide OkHttpClient
    @Provides @Named("cached")
    @Singleton
    fun ProvidesOkHtppClient(cache: Cache, httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    //3. Provide OkHttpClient
    @Provides  @Named("non-cached")
    @Singleton
    fun ProvidesOkHtppClientNonCached(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }


    //4. Provides Gson Converter
    @Provides
    @Singleton
    fun ProvidesGsonConvertor(): GsonConverterFactory {
        return GsonConverterFactory.create(Gson())
    }

    //5 Provide Rxjava2CallAdapter
    @Provides
    @Singleton
    fun ProvidesRxjava2CallAdapter(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create();
    }

    //Provide Retrofit Client
    @Provides
    @Singleton
    fun ProvidesRetrofit(@Named("cached")
        okHttpClient: OkHttpClient,
        rxJava2CallAdapterFactory: RxJava2CallAdapterFactory
        , gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    //Provides CryptService
    @Provides
    @Singleton
    fun ProvidesCryptoService(retrofit: Retrofit):CryptocurrencyService{
        return retrofit.create(CryptocurrencyService::class.java)
    }
}