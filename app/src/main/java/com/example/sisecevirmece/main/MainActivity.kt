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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val database =
            Room.databaseBuilder(this, SoruDatabase::class.java, "SoruDatabase").build()
        CoroutineScope(Dispatchers.IO).launch {
            DatabaseInit(database)//Coroutine içinde kurduk ki arayüz etkilenmesin
            //TODo Buraya transaction düşün buradan soruya ulaşılıyor
            OyunViewModel.setContext(applicationContext)
        }
        setContentView(R.layout.activity_main)
        val oynaDugmesi : Button=findViewById<Button>(R.id.oynaDugmesi)
        oynaDugmesi.setOnClickListener(){
            //Oyuna geç
            oyunaGec()
        }

    }

    private fun oyunaGec() {
        val intent=Intent(this, OyuncuSayisi::class.java)
        startActivity(intent)
    }



}
