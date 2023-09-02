package com.example.sisecevirmece.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.sisecevirmece.R
import com.example.sisecevirmece.data.DatabaseInit
import com.example.sisecevirmece.data.SoruDatabase
import com.example.sisecevirmece.oyun_ayarlama.OyuncuSayisi

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val database= Room.databaseBuilder(this,SoruDatabase::class.java,"SoruDatabase").build()
        DatabaseInit(database)//Oyunun başında soru veritabanını kurduk
        setContentView(R.layout.activity_main)
        var oynaDugmesi : Button=findViewById<Button>(R.id.oynaDugmesi)
        oynaDugmesi.setOnClickListener(){
            //Oyuna geç
            oyunaGec()
        }

    }

    private fun oyunaGec() {
        var intent:Intent=Intent(this, OyuncuSayisi::class.java)
        startActivity(intent)
    }



}
