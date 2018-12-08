package com.kotlin.rxjava.component

import com.kotlin.rxjava.model.ApiManager
import com.kotlin.rxjava.module.AppModule
import com.kotlin.rxjava.module.NetModule
import dagger.Component
import javax.inject.Singleton

@Component(modules = arrayOf(AppModule::class,NetModule::class))
@Singleton
interface NetComponent {
    fun inject(apiManager: ApiManager)
}