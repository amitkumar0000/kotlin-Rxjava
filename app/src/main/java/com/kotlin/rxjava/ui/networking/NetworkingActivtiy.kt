package com.kotlin.rxjava.ui.networking

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import com.kotlin.rxjava.R
import android.support.v7.widget.LinearLayoutManager
import retrofit2.Retrofit
import com.kotlin.rxjava.model.Crypto
import com.kotlin.rxjava.ui.networking.viewmodel.CryptoViewmodel


class NetworkingActivtiy : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var recyclerViewAdapter: RecyclerViewAdapter
    lateinit var retrofit: Retrofit
    lateinit var cryptVm:CryptoViewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_networking_activtiy)

        cryptVm = ViewModelProviders.of(this).get(CryptoViewmodel::class.java)

        recyclerView = findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerViewAdapter = RecyclerViewAdapter()
        recyclerView.adapter = recyclerViewAdapter


        cryptVm.cryptliveData.observe(this, Observer<List<Crypto.Market>> {
            marketList -> recyclerViewAdapter.setData(marketList!!)
        })

        call()

    }

    fun call(){
        cryptVm.getBtcEth("btc","eth")
    }



}

