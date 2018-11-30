package com.kotlin.rxjava.ui.RxBus

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import java.util.*

class RxBus{

    var bus:PublishSubject<Any>

    init {
        bus = PublishSubject.create()
    }

    fun send(t:Any){
        bus.onNext(t)
    }

    fun toObservable():Observable<Any>{
        return bus;
    }





}