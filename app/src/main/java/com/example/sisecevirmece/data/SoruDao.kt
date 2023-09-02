package com.example.sisecevirmece.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface SoruDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addSorular(sorular:List<Soru>)
    @Query("SELECT * FROM SoruDatabase WHERE id=:id")
    fun getSoruById(id:Int):Soru


}