package com.example.sisecevirmece.oyun_ayarlama

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.sisecevirmece.R

class OyuncuSayisi : AppCompatActivity(), AyarlamaSayfasi {
    private var textView: TextView? =null
    private var seekBar: SeekBar?=null
    private var oyuncuSayisi:Int?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_oyuncu_sayisi)
        textView = findViewById(R.id.oyuncuSayisiText)
        seekBar=findViewById(R.id.oyuncuSayisiAyarlamaCubugu)
        var dugme: Button? =findViewById(R.id.tamamDugmesi)
        dugme?.setOnClickListener(){
            oyunaIlerle()
        }
        seekBar?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                var sayi=progress
                if (sayi % 2 != 0) { //progress tek sayı ise bir arttır, oyuncular birbirleriyle eşleşebilsin diye
                    sayi += 1
                }
                textAyarla(sayi)//Oyuncu sayısı text olarak ayarladık
                oyuncuSayisi = sayi
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })


    }

    override fun oyunaIlerle() {
        var intent=Intent(this, OyuncuAdlari::class.java)
        intent.putExtra("oyuncu_sayisi",oyuncuSayisi)//Bir sonraki aktivitede oyuncu sayısı kadar kutu oluşturabilmek için oyuncu sayısı aktardık
        startActivity(intent)
    }

    private fun textAyarla(sayi:Int){
        val text=Integer.toString(sayi)
        textView?.setText(text)
    }
}