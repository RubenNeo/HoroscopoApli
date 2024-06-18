package com.example.horoscopoapli

import android.content.Context
import android.content.SharedPreferences
import com.example.horoscopoapli.activitys.SecondActivity

class InitSession (context: Context) {
    companion object {
        const val EXTRA_HOROSCOPE_ID = "FAVORITE_HOROSCOPE"
    }


    private val ShaderPref : SharedPreferences

    init {
        ShaderPref = context.getSharedPreferences("Init Session", Context.MODE_PRIVATE)

    }
    fun SetFavoriteHoroscope(id :String){

        val editor = ShaderPref.edit()
        editor.putString(SecondActivity.EXTRA_HOROSCOPE_ID, id)
        editor.apply()
    }
    fun getFavoriteHoroscope() : String? {
        return ShaderPref.getString(SecondActivity.EXTRA_HOROSCOPE_ID, null)
    }

}
