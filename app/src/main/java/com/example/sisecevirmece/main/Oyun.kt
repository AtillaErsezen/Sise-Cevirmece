package com.example.sisecevirmece.main

import android.animation.Animator
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.media.MediaPlayer
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.AdRequest
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.sisecevirmece.R
import com.example.sisecevirmece.oyun_ayarlama.Adlar
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.random.Random

//TODO Google Admobs ekle
class Oyun : AppCompatActivity(){
    private var spinning: Boolean = false
    private var animation: Animation? = null
    private var currentRotation: Float = 0f // Store the current rotation value
    private var bottleStopRotation: Float = 0f // Store the final rotation value after the animation stops
    private var viewModel:OyunViewModel?=null
    private var mediaPlayer: MediaPlayer? = null
    private var interstitialAd: InterstitialAd? = null
    private val reklam_kimligi: String = "ca-app-pub-3824691361038288/3663043331"
    private val test_reklam_kimligi: String = "ca-app-pub-3940256099942544/1033173712"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_oyun)
        animation = AnimationUtils.loadAnimation(this, R.anim.sise_cevirme_animasyon)
        viewModel=ViewModelProvider(this).get(OyunViewModel::class.java)
        mediaPlayer = MediaPlayer.create(this, R.raw.sise_donme_sesi)
    }

    override fun onStart() {
        super.onStart()
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
                override fun onAnimationStart(animation: Animation?) {
                    mediaPlayer?.start()
                }

                override fun onAnimationEnd(animation: Animation?) {
                    mediaPlayer?.stop()
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
    private fun reklamYukle(){
        val adRequest: AdRequest = AdRequest.Builder().build()
        InterstitialAd.load(this, test_reklam_kimligi, adRequest, object:InterstitialAdLoadCallback(){
            override fun onAdFailedToLoad(adError: LoadAdError) {
                Log.d("oyun reklamı", adError.toString())
                interstitialAd = null //reklamımız olmadıği için null yaptık
            }

            override fun onAdLoaded(add: InterstitialAd) {
                Log.d("oyun reklamı", "yüklendi")
                interstitialAd = add // bizim reklamımıza yüklenen reklamı atadık
            }
        })
    }
    private fun reklamYuklenemedi(){
        Toast.makeText(this,R.string.reklam_yukleme_hatasi,Toast.LENGTH_LONG).show()
    }
    private fun reklamTamEkran(){
        interstitialAd?.fullScreenContentCallback = object: FullScreenContentCallback(){

            override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                reklamYuklenemedi()
                interstitialAd = null
            }

            override fun onAdImpression() {
            }

            override fun onAdDismissedFullScreenContent() {
                interstitialAd = null
            }

            override fun onAdShowedFullScreenContent() {

            }
        }
    }
    //TODO aynı isim yasakla
    private fun reklamGoster(interstitialAd: InterstitialAd){
        interstitialAd?.show(this)
    }
    fun dc_diyalog_olustur(secilen:String,esi:String){
        reklamYukle()
        reklamTamEkran()
        setContentView(R.layout.dc_diyalog)
        val mesaj = secilen + "! Seç bakalım! Doğruluk mu, cesaret mi?"
        findViewById<TextView?>(R.id.dc_text).text=mesaj//Başlığa oyuncu adını ekledik
        val dogruluk=findViewById<Button>(R.id.dogruluk_dugme)
        dogruluk.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch{
                val soru=viewModel?.soruSor(SoruTipi.DOGRULUK)
                //TODO burada reklam girecek
                // ca-app-pub-3940256099942544/1033173712 test reklamı kimliği
                reklamGoster(interstitialAd!!)
                runOnUiThread{
                    setContentView(R.layout.soru_diyalogu)
                    //findViewById<TextView>(R.id.soru_text).text=soru
                    findViewById<TextView>(R.id.soru_text).text="Question"
                    //TODO remove above line
                    val button=findViewById<Button>(R.id.soru_tamam)
                    button.setOnClickListener{
                        setContentView(R.layout.activity_oyun)
                        seyleriEkle(oyuncuSayisiAl())

                    }
                }
            }

        }
        val cesaret=findViewById<Button>(R.id.cesaret_dugme)
        cesaret.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch{
                val soru=viewModel?.soruSor(SoruTipi.CESARET)
                //TODO burada reklam girecek
                //  test reklamı kimliği
                reklamGoster(interstitialAd!!)
                runOnUiThread{
                    setContentView(R.layout.soru_diyalogu)
                    //findViewById<TextView>(R.id.soru_text).text=soru
                    findViewById<TextView>(R.id.soru_text).text="Question"
                    //TODO remove above line
                    val button=findViewById<Button>(R.id.soru_tamam)
                    button.setOnClickListener{
                        setContentView(R.layout.activity_oyun)
                        seyleriEkle(oyuncuSayisiAl())

                    }
                }
            }


        }
    }

    //TODO isimleri ayarlarken isim girilmek zorunda ekle
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
        Log.d("oyuncu listesi",Adlar.getAdListesi()[indeks])
        oyuncu.text = Adlar.getAdListesi()[indeks]
        oyuncu.setTextColor(Color.BLACK)
        return oyuncu
    }

    private fun oyuncuSayisiAl(): Int {
        return intent.getIntExtra("oyuncu_sayisi", 4)
    }
    override fun onPause(){
        super.onPause()
        mediaPlayer?.pause()
    }
    override fun onDestroy(){
        super.onDestroy()
        mediaPlayer?.release()
    }
}
