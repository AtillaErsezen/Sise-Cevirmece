package com.example.sisecevirmece.oyun_ayarlama

import android.content.Context
import android.widget.ImageButton
import android.widget.LinearLayout
import com.example.sisecevirmece.R

class DugmeFactory {

    companion object{
        fun dugmeOlustur(context: Context): ImageButton{
            val dugme = ImageButton(context)
            dugme.setImageResource(R.drawable.baseline_male_24)
            dugme.setOnClickListener {
               OyuncuAdlariViewModel.dugmeIconDegistir(dugme)
            }
            dugme.layoutParams=
                LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            return dugme
        }
    }
}