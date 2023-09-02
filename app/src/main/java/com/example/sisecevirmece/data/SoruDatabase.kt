package com.example.sisecevirmece.data

import androidx.room.Database
import androidx.room.RoomDatabase
@Database(
    entities = [Soru::class],
    version=1,
    exportSchema = false
)
abstract class SoruDatabase:RoomDatabase() {
    abstract val dao:SoruDao
}