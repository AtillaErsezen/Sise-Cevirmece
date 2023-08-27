package com.example.sisecevirmece.data

import androidx.room.Dao
import androidx.room.Upsert

@Dao
interface SoruDao {
    @Upsert
    fun addSorular(sorular:List<String>)
    fun getSorular():List<String>
    fun getSoruById(id:Int):String


}