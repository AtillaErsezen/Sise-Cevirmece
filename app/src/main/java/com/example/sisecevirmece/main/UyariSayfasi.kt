package com.example.sisecevirmece.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.sisecevirmece.R

class UyariSayfasi : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uyari_sayfasi)
        var dugme: Button?=findViewById(R.id.uyari_tamam)
        dugme?.setOnClickListener(){
            oyunaGec()
        }
    }

    private fun oyunaGec() {
        var intent= Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}