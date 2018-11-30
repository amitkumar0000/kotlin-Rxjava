package com.kotlin.rxjava

import android.app.Application
import com.kotlin.rxjava.model.Events
import com.kotlin.rxjava.ui.RxBus.RxBus
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import java.util.function.Consumer

class MyApplication : Application() {

    var rxbus:RxBus

    init {
        rxbus = RxBus()
    }

    override fun onCreate() {
        super.onCreate()
    }

    fun sendAutoEvent(){

        Observable.timer(2, TimeUnit.SECONDS)
            .subscribe { rxbus.send(Events.AutoEvent()) }

    }
}