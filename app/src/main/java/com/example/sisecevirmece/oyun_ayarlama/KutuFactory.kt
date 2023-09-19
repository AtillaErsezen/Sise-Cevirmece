package com.example.sisecevirmece.oyun_ayarlama

import android.content.Context
import android.text.InputFilter
import android.text.InputType
import android.widget.LinearLayout
import com.google.android.material.textfield.TextInputEditText

class KutuFactory {
   companion object {
       fun kutuOlustur(context: Context, i: Int): TextInputEditText{
           val kutu = TextInputEditText(context)
           kutu.hint = "İsim girin"
           kutu.id = i //isim listesi indeks işi kolay yoldan olsun diye id verdik
           kutuRepository.addKutu(kutu)//girilen isimlere tamam düğmesinden erişebilmek için kutuDeposunda kutuyu depoladık
           kutu.freezesText=true//Girilen ismi kendisinde tutsun diye, başka uygulama açılmışken vs.
           kutu.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(12))
           kutu.inputType= InputType.TYPE_TEXT_VARIATION_PERSON_NAME
           kutu.setTextIsSelectable(false) //İsim sonradan değiştirilemesin diye false yaptık
           kutu.layoutParams = LinearLayout.LayoutParams(
               LinearLayout.LayoutParams.MATCH_PARENT,
               LinearLayout.LayoutParams.WRAP_CONTENT
           )

           return kutu
       }
   }
}