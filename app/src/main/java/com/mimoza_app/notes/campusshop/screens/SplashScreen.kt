package com.mimoza_app.notes.campusshop.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent =  Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}