package com.example.sisecevirmece.main

import android.animation.Animator
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sisecevirmece.R
import com.example.sisecevirmece.oyun_ayarlama.Adlar
import kotlin.random.Random

class Oyun : AppCompatActivity(){
    private var spinning: Boolean = false
    private var animation: Animation? = null
    private var currentRotation: Float = 0f // Store the current rotation value
    private var bottleStopRotation: Float = 0f // Store the final rotation value after the animation stops
    private val d_tr:SharedPreferences=getSharedPreferences("d_tr",Context.MODE_PRIVATE)
    private val d_eng:SharedPreferences=getSharedPreferences("d_eng",Context.MODE_PRIVATE)
    private val c_tr:SharedPreferences=getSharedPreferences("c_tr",Context.MODE_PRIVATE)
    private val c_eng:SharedPreferences=getSharedPreferences("c_eng",Context.MODE_PRIVATE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_oyun)
        animation = AnimationUtils.loadAnimation(this, R.anim.sise_cevirme_animasyon)
        //oyuncu sayısı al
        val oyuncuSayisi = oyuncuSayisiAl()
        seyleriEkle(oyuncuSayisi)
    }
    //TODo minimum oyuncu sayısı iki olsun
    public fun siseCevir(view: View) {
        if (!spinning) {
            spinning = true //Dönüyorken bir daha döndürmeyi önlemek için
            val sise = findViewById<ImageView>(R.id.sise)
            val oyuncuIndeks=Random.nextInt(oyuncuSayisiAl())//Rastgele oyuncu sec
            val oyuncuAci=oyuncuAciDegeriBelirle(oyuncuSayisiAl(),oyuncuIndeks)//Seçilen oyuncu için açı değeri belirle


            // Start the spinning animation
            animation!!.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) {}

                override fun onAnimationEnd(animation: Animation?) {
                    sise.rotation=oyuncuAci//Oyuncuyu burada seçtik
                    bottleStopRotation = sise.rotation % 360 // Store the final rotation value
                   val animation= modifyAndStartAnimation(sise,oyuncuAci)//Burada yavaşlama animasyon başlat
                    animation.addListener(object: Animator.AnimatorListener{
                        override fun onAnimationStart(animation: Animator) {

                        }

                        override fun onAnimationEnd(animation: Animator) {
                            //seçilen isimlerden diyalog olustur
                            val secilen_oyuncu_isim= Adlar.getAdListesi().get(oyuncuIndeks)
                            val secilen_oyuncu_esi= Adlar.getAdListesi().get(Adlar.size()-oyuncuIndeks-1)
                            dc_diyalog_olustur(secilen_oyuncu_isim,secilen_oyuncu_esi)
                        }

                        override fun onAnimationCancel(animation: Animator) {
                        }

                        override fun onAnimationRepeat(animation: Animator) {
                        }

                    })

                    spinning = false
                }

                override fun onAnimationRepeat(animation: Animation?) {}
            })
            sise.startAnimation(animation)
        }
    }
    fun dc_diyalog_olustur(secilen:String,esi:String){
        setContentView(R.layout.dc_diyalog)
        val mesaj = secilen + "! Seç bakalım! Doğruluk mu, cesaret mi?"
        findViewById<TextView?>(R.id.dc_text).text=mesaj//Başlığa oyuncu adını ekledik
        val dogruluk=findViewById<Button>(R.id.dogruluk_dugme)
        dogruluk.setOnClickListener(){
            //tODO doğruluk sorusu sor
            soruSor(secilen,esi,true)
        }
        val cesaret=findViewById<Button>(R.id.cesaret_dugme)
        cesaret.setOnClickListener(){
            //tODO cesaret sorusu sor
            soruSor(secilen,esi,false)
        }
    }

    private fun soruSor(secilen: String, esi: String,soru_tipi:Boolean) {
        setContentView(R.layout.soru_diyalogu)
            if(soru_tipi){//Doğruluk
                //todo Doğruluk sorusu çek shared preferences
                val kimlik=Random(0).nextInt(49)
                val soru=d_tr.getString(kimlik.toString(),null)
                if(soru==null){
                    Toast.makeText(this,"soruya ulaşılamadı",Toast.LENGTH_LONG).show()
                }
                findViewById<TextView>(R.id.soru_text).text=soru
            }else{//Cesaret
                val kimlik=Random(1).nextInt(49)
                val soru=c_tr.getString(kimlik.toString(),null)
                if(soru==null){
                    Toast.makeText(this,"soruya ulaşılamadı",Toast.LENGTH_LONG).show()
                }
                findViewById<TextView>(R.id.soru_text).text=soru
            }



    }

    //TODO isimleri ayarlarken isim girilmek zorunda ekle
    //TODO isimlerde türkçe karakter girilmiyor
    private fun oyuncuAciDegeriBelirle(oyuncuSayisi:Int,oyuncuIndeks:Int): Float {
        return (360/oyuncuSayisi)*(oyuncuIndeks+1).toFloat()
    }//FIXME dört kişi olunca açı değeri sıkıntılı oluyor

    private fun seyleriEkle(oyuncuSayisi: Int) {
        val masa_layout = findViewById<CircularLayout>(R.id.masa_layout)
        for (i in 0 until oyuncuSayisi) {
            masa_layout.addView(oyuncuOlustur(i, oyuncuSayisi))
        }
    }

    private fun oyuncuOlustur(indeks: Int, oyuncuSayisi: Int): View? {
        val oyuncu = TextView(this)
        oyuncu.text = Adlar.getAdListesi()[indeks]
        oyuncu.setTextColor(Color.BLACK)
        return oyuncu
    }

    private fun oyuncuSayisiAl(): Int {
        return intent.getIntExtra("oyuncu_sayisi", 4)
    }
    override fun onDestroy(){
        super.onDestroy()
    }
}
