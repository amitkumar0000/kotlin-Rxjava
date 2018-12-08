package com.kotlin.rxjava

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.kotlin.rxjava.ui.RxBus.RxBusActivity
import com.kotlin.rxjava.ui.SerachActivity
import com.kotlin.rxjava.ui.networking.NetworkingActivtiy

class SelectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun onClick(view: View){
        when(view.id){
            R.id.startRxBusActivity -> {
                (application as MyApplication).sendAutoEvent()
                startActivity(Intent(this,RxBusActivity::class.java))
            }

            R.id.networkActivity -> {
                startActivity(Intent(this,NetworkingActivtiy::class.java))

            }
            R.id.searchAt->{
                startActivity(Intent(this, SerachActivity::class.java))
            }

        }
    }
}
