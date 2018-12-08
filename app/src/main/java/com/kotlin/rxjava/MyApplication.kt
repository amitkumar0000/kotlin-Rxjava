package com.kotlin.rxjava

import android.app.Application
import com.kotlin.rxjava.component.CryptVMComponent
import com.kotlin.rxjava.component.DaggerCryptVMComponent
import com.kotlin.rxjava.component.DaggerNetComponent
import com.kotlin.rxjava.component.NetComponent
import com.kotlin.rxjava.model.Events
import com.kotlin.rxjava.module.AppModule
import com.kotlin.rxjava.module.CryptVMModule
import com.kotlin.rxjava.module.NetModule
import com.kotlin.rxjava.ui.RxBus.RxBus
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import java.util.function.Consumer

class MyApplication : Application() {

    var rxbus: RxBus

    companion object {
        lateinit var netComponent: NetComponent
        lateinit var cryptVMComponent: CryptVMComponent
    }

    init {
        rxbus = RxBus()
    }

    override fun onCreate() {
        super.onCreate()

        buildComponents();
    }

    private fun buildComponents() {
        buildNetComponent()

        buildCryptVmComponent()
    }

    private fun buildCryptVmComponent() {
        cryptVMComponent = DaggerCryptVMComponent.builder()
            .cryptVMModule(CryptVMModule())
            .build()
    }

    private fun buildNetComponent() {
        netComponent = DaggerNetComponent.builder()
            .appModule(AppModule(this))
            .netModule(NetModule("https://api.cryptonator.com/api/full/"))
            .build()
    }


    fun sendAutoEvent() {

        Observable.timer(2, TimeUnit.SECONDS)
            .subscribe { rxbus.send(Events.AutoEvent()) }

    }
}