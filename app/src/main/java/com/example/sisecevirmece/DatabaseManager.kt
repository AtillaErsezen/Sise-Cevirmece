package com.example.sisecevirmece

import android.content.Context
import androidx.room.Room
import com.example.sisecevirmece.data.SoruDatabase

/*
veritabanı için tek referans oluşturulsun istedik, bunun için veritabanı referansını burada tutacağız
 */
object DatabaseManager {
    private var database: SoruDatabase?=null
    fun getDatabase(context: Context):SoruDatabase{
        if(database==null){ //database null ise burada oluştur
            database= Room.databaseBuilder(
                context,
                SoruDatabase::class.java,
                "SoruDatabase"
            ).build()
        }
        return database!! //null döndürmesin
    }
}