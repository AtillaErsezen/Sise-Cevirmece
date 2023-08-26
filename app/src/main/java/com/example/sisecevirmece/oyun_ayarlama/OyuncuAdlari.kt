package com.example.sisecevirmece.oyun_ayarlama

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.opengl.Visibility
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.InputType
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.sisecevirmece.KutuluSayfa
import com.example.sisecevirmece.main.Oyun
import com.example.sisecevirmece.R
import com.google.android.material.textfield.TextInputEditText

class kutuDeposu{
   // isimleri adlar sınıfına göndermeden önce burada depolar, text changed ile olan karışıkları önlemek ve tamam düğmesinden isimlere erişilemediği için

    companion object{
        private val kutuListesi=mutableListOf<TextInputEditText>()
        fun kutuAl(kutu:TextInputEditText){
            kutuListesi.add(kutu)
        }
        fun kutuListesiAl():List<TextInputEditText>{
            return kutuListesi
        }
        fun hataVarMi():Int{
            for(kutu in kutuListesi){
                if(kutu.error==null)
                    continue
                else  return View.INVISIBLE
            }
            return View.VISIBLE
        }
    }
    class isimCozumle{//kutu deposundan kutuları alır ve isimlerini liste halinde döndürür
        companion object {
            private val isimListesi=mutableListOf<String>()
            fun getAdListesi():List<String> {
                isimleriCoz()//kutulardan isimleri çıkardık
                return isimListesi
            }

        private fun isimleriCoz() {
            for(i in 0 until kutuListesiAl().size){
                val isim:String= kutuListesiAl().get(i).text.toString()
                isimEkle(isim)
            }
        }
        private fun isimEkle(isim:String){
            isimListesi.add(isim)
        }
    }

    }
}

class OyuncuAdlari : AppCompatActivity(), AyarlamaSayfasi, KutuluSayfa {
    private var kutu_container:LinearLayout?=null
    private var iconSec:Boolean ?=true
    private var tamamDugmesi: Button? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_oyuncu_adlari)
        kutu_container=findViewById(R.id.editTextsContainer)

        tamamDugmesi= findViewById(R.id.oyuncu_isimleri_tamam)
        tamamDugmesi?.setOnClickListener {
            Adlar.setAdListesi(kutuDeposu.isimCozumle.getAdListesi())
            oyunaIlerle()
        }
        var oyuncuSayisi:Int?=oyuncuSayisiAl() //oyuncuSayısı alma
        seyleriEkle(oyuncuSayisi!!)//!! non null demek
        //TODo kısayollara bak
        //
    }
    override fun seyleriEkle(oyuncuSayisi:Int) {
        for(i in 0 until oyuncuSayisi){
            kutu_container?.addView(kutuOlustur())//Kutuları oluşturup ekledik
            kutu_container?.addView(dugmeOlustur())//Düğmeleri oluşturup ekledik
        }
    }

    private fun dugmeOlustur(): View {
        val dugme= ImageButton(this)
        dugme.setImageResource(R.drawable.baseline_male_24)
        dugme.setOnClickListener {
           if(iconSec!!){
               dugme.setImageResource(R.drawable.baseline_female_24)
           }else{
               dugme.setImageResource(R.drawable.baseline_male_24)
           }
                iconSec=!iconSec!!
        }
        dugme.layoutParams=LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT)
        return dugme
    }

    private fun karakterSiniriAyarla(text:TextInputEditText){
        val karakter_siniri=12
        val filterArray=arrayOf<InputFilter>(InputFilter.LengthFilter(karakter_siniri))
        text.filters=filterArray
    }
    override fun kutuOlustur() : TextInputEditText {
        val kutu=TextInputEditText(this)
        kutu.hint = "İsim girin"
        kutuDeposu.kutuAl(kutu)//girilen isimlere tamam düğmesinden erişebilmek için kutuDeposunda kutuyu depoladık
        //FIXME isimleri en son göndercez listeye
        kutu.freezesText=true//Girilen ismi kendisinde tutsun diye, başka uygulama açılmışken vs.
        karakterSiniriAyarla(kutu)//Karakter uzunluk sınırı ayarladık
        boslukEngelle(kutu)
        //ayniIsımEngelle(kutu)
        kutu.inputType= InputType.TYPE_TEXT_VARIATION_PERSON_NAME
        kutu.setTextIsSelectable(false) //İsim sonradan değiştirilemesin diye false yaptık
        //layoutParams
        kutu.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        return kutu
    }

    private fun ayniIsımEngelle(kutu: TextInputEditText) {
        val textWatcher=object:TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val string=s.toString()
                //FIXME düzgün çalışmıyor
                if(kutuDeposu.isimCozumle.getAdListesi().contains(string) and !string.isEmpty())
                    kutu.error="Aynı isimde birden fazla oyuncu olamaz"
                else
                    kutu.error=null
            }

            override fun afterTextChanged(s: Editable?) {
            }

        }
        kutu.addTextChangedListener(textWatcher)
    }

    private fun boslukEngelle(kutu: TextInputEditText) {
        val textWatcher=object:TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val string:String=s.toString()

                if(string.trim().isEmpty())//İsim yeri boş ise hata yaz
                    kutu.error = "İsim yeri boş bırakılamaz"
                //Hata kontrolü
                tamamDugmesi!!?.setVisibility(kutuDeposu.hataVarMi())
            }

            override fun afterTextChanged(s: Editable?) {
            }

        }
        kutu.addTextChangedListener(textWatcher)
    }
//TODo aynı isimde oyuncu olmamalı
    private fun oyuncuSayisiAl(): Int {
        return intent.getIntExtra("oyuncu_sayisi",4)
    }

    override fun oyunaIlerle() {
        sorulariAyarla()
        val intent= Intent(this, Oyun::class.java)
        intent.putExtra("oyuncu_sayisi",oyuncuSayisiAl())
        startActivity(intent)
    }

    private fun sorulariAyarla() {
        //ilk defa mı kontrol et
        val ilk:SharedPreferences=getSharedPreferences("ilk_mi", Context.MODE_PRIVATE)
        val ilk_mi=ilk.getBoolean("ilk_mi", false)
        if(ilk_mi){
            val d_tr=getSharedPreferences("d_tr",Context.MODE_PRIVATE)
            val c_tr=getSharedPreferences("c_tr",Context.MODE_PRIVATE)
            for(i in 0..100){

            }
        }
    }
}