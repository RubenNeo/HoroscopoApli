package com.example.horoscopoapli

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SecondActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_HOROSCOPE_ID = "List_Horoscope"

    }

    lateinit var horoscope: Horoscope


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val initPage = findViewById<FloatingActionButton>(R.id.ButtonInit)
        initPage.setOnClickListener() {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        val id = intent.getStringExtra(EXTRA_HOROSCOPE_ID)
        horoscope = HoroscopeProvider.findById(id!!)!!


        findViewById<TextView>(R.id.textName).setText(horoscope.name)
        findViewById<ImageView>(R.id.ImageViewH).setImageResource(horoscope.logo)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_activity_second, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.favorite -> {
                Log.i("MENU", "He hecho click en el menu de favorito")
                true
            }
            R.id.ic_share -> {
                Log.i("MENU", "He hecho click en el menu de compartir")
                true
            }
            R.id.favoriteEmpty -> {
                Log.i("Menu", "He hecho click en el boton del corazon vacio")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}



