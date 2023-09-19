package com.example.sisecevirmece.oyun_ayarlama

import android.util.Log
import android.view.View
import android.widget.ImageButton
import androidx.lifecycle.ViewModel
import com.example.sisecevirmece.R

class OyuncuAdlariViewModel : ViewModel() {

    private var geciciAdListesi = mutableListOf<String>()
    private var hataDurumu: Int = View.INVISIBLE
    //Adlistesi ayarla
    //düğme oluştur
    //karakter sınırı ayarla
    //aynı isim engelle
    fun adListesiAyarla(){

    }

    fun hataKontrol(){
        for (kutu in kutuRepository.getKutuListesi()){
            val ad= kutu.text.toString()
            if (ad.trim().isEmpty()){
                kutu.error = "İsim yeri boş bırakılamaz"
            }
            else if (kutuRepository.getAdListesi().count {it == ad} > 1){
                kutu.error = "Aynı isimle birden fazla oyuncu olamaz"
            }else{
                kutu.error = null
            }
        }
        Log.d("ad listesi", kutuRepository.getAdListesi().toString())
    }
    companion object {
        private var iconSec: Boolean?=true
        fun dugmeIconDegistir(dugme: ImageButton) {
            if(iconSec == true){
                dugme.setImageResource(R.drawable.baseline_female_24)
            }else{
                dugme.setImageResource(R.drawable.baseline_male_24)
            }
            iconSec=!iconSec!!
        }
    }
}