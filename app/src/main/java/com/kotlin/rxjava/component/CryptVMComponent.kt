package com.kotlin.rxjava.component

import com.kotlin.rxjava.module.CryptVMModule
import com.kotlin.rxjava.ui.networking.viewmodel.CryptoViewmodel
import dagger.Component

@Component(modules = arrayOf(CryptVMModule::class))
interface CryptVMComponent {
    fun inject(vm: CryptoViewmodel)
}