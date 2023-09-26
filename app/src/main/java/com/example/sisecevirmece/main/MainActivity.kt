package com.example.sisecevirmece.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.sisecevirmece.R
import com.example.sisecevirmece.data.DatabaseInit
import com.example.sisecevirmece.data.SoruDatabase
import com.example.sisecevirmece.oyun_ayarlama.OyuncuSayisi
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
//TODO oyna düğmesi altına reklam
class MainActivity : AppCompatActivity() {
    private lateinit var mAdView: AdView
    override fun onCreate(savedInstanceState: Bundle?) {
        //TODO ana sayfa resmi, logo koy
        super.onCreate(savedInstanceState)
        val database =
            Room.databaseBuilder(this, SoruDatabase::class.java, "SoruDatabase").build()
        CoroutineScope(Dispatchers.IO).launch {
            DatabaseInit(database)//Coroutine içinde kurduk ki arayüz etkilenmesin
            OyunViewModel.setContext(applicationContext)
        }
        setContentView(R.layout.activity_main)
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
        val oynaDugmesi : Button=findViewById(R.id.oynaDugmesi)
        oynaDugmesi.setOnClickListener{
            //Oyuna geç
            oyunaGec()
        }

    }

    private fun oyunaGec() {
        val intent=Intent(this, OyuncuSayisi::class.java)
        startActivity(intent)
    }



}
