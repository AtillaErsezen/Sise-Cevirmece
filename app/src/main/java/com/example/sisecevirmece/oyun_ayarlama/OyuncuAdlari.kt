package com.example.sisecevirmece.oyun_ayarlama

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.sisecevirmece.main.Oyun
import com.example.sisecevirmece.R
import com.google.android.material.textfield.TextInputEditText



class OyuncuAdlari : AppCompatActivity(), AyarlamaSayfasi {
    private var kutu_container:LinearLayout?=null

    private var tamamDugmesi: Button? =null
    private var viewModel: OyuncuAdlariViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_oyuncu_adlari)
        kutu_container=findViewById(R.id.editTextsContainer)
        viewModel = ViewModelProvider(this)[OyuncuAdlariViewModel::class.java]
        tamamDugmesi= findViewById(R.id.oyuncu_isimleri_tamam)
        tamamDugmesi?.setOnClickListener {
                Adlar.setAdListesi(kutuRepository.getAdListesi())
                oyunaIlerle()
        }
        var oyuncuSayisi:Int?=oyuncuSayisiAl() //oyuncuSayısı alma
        seyleriEkle(oyuncuSayisi!!)//!! non null demek
    }
    private fun seyleriEkle(oyuncuSayisi: Int){
        for(i in 0 until oyuncuSayisi){
            val kutu:TextInputEditText = KutuFactory.kutuOlustur(this, i)
            val textWatcher=object: TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                   //her text değiştiğinde bütün kutuları kontrol et aynı ad kontrolü vs. için
                    viewModel?.hataKontrol() // isimde duruma göre hata koyduk
                    //Hata kontrolü
                    tamamDugmesi!!.visibility = kutuRepository.hataVarMi() // tümkutuların kontrolü
                }

                override fun afterTextChanged(s: Editable?) {

                }

            }
            kutu.addTextChangedListener(textWatcher)
            kutu_container?.addView(kutu)
            val button = DugmeFactory.dugmeOlustur(this)
            //Kutuları oluşturup ekledik
            kutu_container?.addView(button)//Düğmeleri oluşturup ekledik
        }
    }
    private fun oyuncuSayisiAl(): Int {
        return intent.getIntExtra("oyuncu_sayisi",4)
    }

    override fun oyunaIlerle() {
        val intent= Intent(this, Oyun::class.java)
        intent.putExtra("oyuncu_sayisi",oyuncuSayisiAl())
        startActivity(intent)
    }


}