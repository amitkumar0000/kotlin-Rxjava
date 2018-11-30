package com.kotlin.rxjava.ui.networking

import com.kotlin.rxjava.model.Crypto
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path


interface CryptocurrencyService{


    @GET("{coin}-usd")
    fun getCoinData(@Path("coin") coin: String): Observable<Crypto>
}