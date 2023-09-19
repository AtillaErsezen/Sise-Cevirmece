package com.example.sisecevirmece.oyun_ayarlama

import android.view.View
import com.google.android.material.textfield.TextInputEditText

class OyuncuAdlariModel {

}
class kutuRepository{
    // isimleri adlar sınıfına göndermeden önce burada depolar, text changed ile olan karışıkları önlemek ve tamam düğmesinden isimlere erişilemediği için

    companion object{
        private val kutuListesi=mutableListOf<TextInputEditText>()
        fun addKutu(kutu: TextInputEditText){
            kutuListesi.add(kutu)
        }
        fun getKutuListesi():List<TextInputEditText>{
            return kutuListesi
        }
        fun hataVarMi(): Int{
            for(kutu in kutuListesi){
                if(kutu.error == null)
                    continue
                else  return View.INVISIBLE
            }
            return View.VISIBLE
        }
        fun getAdListesi(): List<String>{
            val adListesi = mutableListOf<String>()
            for (kutu in kutuListesi){
                adListesi.add(kutu.id, kutu.text.toString())
            }
            return adListesi
        }
    }
}