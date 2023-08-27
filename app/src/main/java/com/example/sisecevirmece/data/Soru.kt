package com.example.sisecevirmece.data

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class Soru(
    @PrimaryKey val id:Int,
    val soru:String
)