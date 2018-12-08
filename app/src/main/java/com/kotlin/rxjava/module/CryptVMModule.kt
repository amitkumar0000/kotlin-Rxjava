package com.kotlin.rxjava.module

import android.arch.lifecycle.MutableLiveData
import com.kotlin.rxjava.model.ApiManager
import com.kotlin.rxjava.model.Crypto
import dagger.Module
import dagger.Provides

@Module
class CryptVMModule {

    @Provides
    fun ProvidesMutableLiveData():MutableLiveData<List<Crypto.Market>>{
        return MutableLiveData()
    }

    @Provides
    fun ProvidesApiManager(): ApiManager {
        return ApiManager()
    }
}