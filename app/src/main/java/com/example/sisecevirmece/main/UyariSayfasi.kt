package com.example.sisecevirmece.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.sisecevirmece.R
import com.google.android.gms.ads.MobileAds

class UyariSayfasi : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uyari_sayfasi)
        val dugme: Button?=findViewById(R.id.uyari_tamam)
        MobileAds.initialize(this) {}
        dugme?.setOnClickListener(){
            oyunaGec()
        }
    }

    private fun oyunaGec() {
        var intent= Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}