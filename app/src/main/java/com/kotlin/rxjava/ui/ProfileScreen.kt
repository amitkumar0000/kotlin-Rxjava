package com.kotlin.rxjava.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import com.kotlin.rxjava.R
import kotlinx.android.synthetic.main.activity_profile_screen.*

class ProfileScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_screen)
        setSupportActionBar(toolbar)


    }

}
