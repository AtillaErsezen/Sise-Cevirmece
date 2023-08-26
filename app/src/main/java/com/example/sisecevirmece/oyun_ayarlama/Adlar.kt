package com.example.sisecevirmece.oyun_ayarlama

import android.util.Log

abstract class Adlar(){
    companion object{
    private var adListesi=mutableListOf<String>()//Sonradan eleman ekleyebilmek için böyle tanımladık
        fun setAdListesi(list:List<String>){//Listeye isim ekler
            adListesi =list.toMutableList()
        }
        fun getAdListesi():List<String>{
            Log.d("liste", adListesi.toString())
            return adListesi
        }
        fun size():Int{
            return adListesi.size
        }
        }

}
