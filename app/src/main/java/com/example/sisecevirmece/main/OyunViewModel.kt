package com.example.sisecevirmece.main

import android.animation.Animator
import android.content.Context
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sisecevirmece.DatabaseManager
import com.example.sisecevirmece.data.SoruDatabase
import com.example.sisecevirmece.oyun_ayarlama.Adlar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.random.Random

class OyunViewModel: ViewModel() { //ViewModel extend ettik ki activity lifecycle dışında kalsın

    companion object {
        private lateinit var context: Context
        fun setContext(pcontext: Context) {
            context = pcontext
        }
    }
    suspend fun soruSor(
        soruTipi: SoruTipi
    ): String {//asenkron işler yaptığımız için suspend
        return if (soruTipi == SoruTipi.DOGRULUK) {
            val randomInt = Random.nextInt(SoruSayilari.DOGRULUK_SORU_SAYISI)
            val database = DatabaseManager.getDatabase(context)
            database.dao.getSoruById(randomInt).soru
        } else {
            val randomInt = Random.nextInt(SoruSayilari.DOGRULUK_SORU_SAYISI)
            val database = DatabaseManager.getDatabase(context)
            database.dao.getSoruById(randomInt).soru
        }

    }

}