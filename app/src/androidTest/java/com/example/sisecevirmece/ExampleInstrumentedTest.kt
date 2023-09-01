package com.example.sisecevirmece

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.sisecevirmece.data.DatabaseInit
import com.example.sisecevirmece.data.SoruDatabase
import com.example.sisecevirmece.main.MainActivity

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun databaseTest() {
        val fake_database= Room.databaseBuilder(InstrumentationRegistry.getInstrumentation().context,SoruDatabase::class.java,"SoruDatabase").build()
        DatabaseInit(fake_database)
        assertEquals("Have you ever broken up with someone because you were fat?",fake_database.dao.getSoruById(0))
    }
}