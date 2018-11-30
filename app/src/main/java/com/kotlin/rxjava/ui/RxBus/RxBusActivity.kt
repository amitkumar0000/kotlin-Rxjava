package com.kotlin.rxjava.ui.RxBus

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.kotlin.rxjava.MyApplication
import com.kotlin.rxjava.R
import com.kotlin.rxjava.model.Events
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class RxBusActivity : AppCompatActivity() {

    lateinit var compositeDisposable: CompositeDisposable
    lateinit var rxbusTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx_bus)

        rxbusTextView = findViewById(R.id.rxBusText)

        compositeDisposable = CompositeDisposable()

        compositeDisposable.add((application as MyApplication)
            .rxbus
            .bus
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { `object` ->
                if (`object` is Events.TapEvent)
                    rxbusTextView.text = "Tap Event Received"
                else if (`object` is Events.AutoEvent)
                    rxbusTextView.text = "Auto Event Received"
            }
        )
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.click -> {
                (application as MyApplication).rxbus.send(Events.TapEvent())
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}
