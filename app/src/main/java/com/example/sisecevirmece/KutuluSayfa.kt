package com.example.sisecevirmece

import android.widget.EditText

interface KutuluSayfa {
    fun kutuOlustur():EditText
    fun seyleriEkle(oyuncuSayisi:Int)
}