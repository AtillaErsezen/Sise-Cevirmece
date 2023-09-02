package com.example.sisecevirmece

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.sisecevirmece.data.DatabaseInit
import com.example.sisecevirmece.data.Soru
import com.example.sisecevirmece.data.SoruDatabase
import com.example.sisecevirmece.main.MainActivity
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    private lateinit var fakeDatabase:SoruDatabase

        @Before
        fun setup() {
            fakeDatabase = Room.inMemoryDatabaseBuilder(
                InstrumentationRegistry.getInstrumentation().context,
                SoruDatabase::class.java
            )
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)

                    }
                })
                .build()
            DatabaseInit(fakeDatabase)
        }
    //cleanup metodunu kaldırdık, bu tarz testlerde test bittikten sonra otomatik kapanıyormuş
            @Test
            fun databaseTest(){
                    assertEquals(
                        "Have you ever broken up with someone because you were fat?",
                        fakeDatabase.dao.getSoruById(1).soru
                    )
        assertEquals(
            "Has anyone ever caught you having sex?",
            fakeDatabase.dao.getSoruById(2).soru
        )
            }
}