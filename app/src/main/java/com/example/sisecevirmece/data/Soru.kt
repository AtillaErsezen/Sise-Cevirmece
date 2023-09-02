package com.example.sisecevirmece.data

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "SoruDatabase")
data class Soru(
    val soru:String,
    @PrimaryKey(autoGenerate = true)
    val id:Int=0
)