package com.kotlin.rxjava.model

import com.kotlin.rxjava.MyApplication
import com.kotlin.rxjava.model.Crypto
import com.kotlin.rxjava.ui.networking.CryptocurrencyService
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ApiManager {

    @Inject
    lateinit var cryptocurrencyService: CryptocurrencyService

    init {
        MyApplication.netComponent.inject(this)
    }

    fun get(query:String): Observable<Crypto> {
        return cryptocurrencyService
            .getCoinData(query)
            .subscribeOn(Schedulers.single())
    }


}