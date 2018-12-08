package com.kotlin.rxjava.ui.networking.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.kotlin.rxjava.MyApplication
import com.kotlin.rxjava.model.Crypto
import com.kotlin.rxjava.module.ApiManager
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CryptoViewmodel : ViewModel() {
    @Inject
    lateinit var cryptliveData: MutableLiveData<List<Crypto.Market>>
    @Inject
    lateinit var apiManager: ApiManager

    init {
        MyApplication.cryptVMComponent.inject(this)
    }

    fun getBtcEth(q1: String, q2: String) {
        val btcObservable = apiManager.get(q1)
            .map { result -> Observable.fromIterable(result.ticker!!.markets) }
            .flatMap { x -> x }
            .filter { y ->
                y.coinName = "btc"
                true
            }.toList().toObservable()

        val ethObservable = apiManager.get(q2)
            .map { result -> Observable.fromIterable(result.ticker!!.markets) }
            .flatMap { x -> x }
            .filter { y ->
                y.coinName = "eth"
                true
            }.toList().toObservable()


        Observable.merge(btcObservable, ethObservable)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Consumer<List<Crypto.Market>?> {
                override fun accept(t: List<Crypto.Market>?) {
                    postValue(t)
                }

            }, object : Consumer<Throwable?> {
                override fun accept(t: Throwable?) {
                }
            });
    }

    fun postValue(market: List<Crypto.Market>?) {
        cryptliveData.postValue(market)
    }
}