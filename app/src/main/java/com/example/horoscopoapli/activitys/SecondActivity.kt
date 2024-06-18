package com.example.horoscopoapli.activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.example.horoscopoapli.util.InitSession
import com.example.horoscopoapli.R
import com.example.horoscopoapli.data.Horoscope
import com.example.horoscopoapli.data.HoroscopeProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SecondActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_HOROSCOPE_ID = "List_Horoscope"

    }


    lateinit var horoscope: Horoscope
    var isFavorite = false

    lateinit var Session: InitSession

   lateinit var FavoriteMenuItem: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        Session = InitSession(this)

        val initPage = findViewById<FloatingActionButton>(R.id.ButtonInit)
        initPage.setOnClickListener() {
            finish()
        }


        val id = intent.getStringExtra(EXTRA_HOROSCOPE_ID)
        horoscope = HoroscopeProvider.findById(id!!)!!

        isFavorite = Session.getFavoriteHoroscope()?.equals(horoscope.id) ?: false


        findViewById<TextView>(R.id.textName).setText(horoscope.name)
        findViewById<ImageView>(R.id.ImageViewH).setImageResource(horoscope.logo)
//Para poner el nombre y la fecha arriba en el menu.
        supportActionBar?.setTitle(horoscope.name)
        supportActionBar?.setSubtitle(horoscope.description)

    }

    fun setFavoriteIcon() {
        if (isFavorite) {
            FavoriteMenuItem.setIcon(R.drawable.ic_favorite)
        } else {
            FavoriteMenuItem.setIcon(R.drawable.ic_favorite_empty)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_activity_second, menu)
        FavoriteMenuItem = menu.findItem(R.id.favoriteEmpty)
        setFavoriteIcon()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.favoriteEmpty -> {
                if (isFavorite) {
                    Session.SetFavoriteHoroscope("")
                } else {
                    Session.SetFavoriteHoroscope(horoscope.id)
                }
                isFavorite = !isFavorite
                setFavoriteIcon()
                true

            }


            R.id.ic_share -> {
                val sendIntent = Intent()
                sendIntent.setAction(Intent.ACTION_SEND)
                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
                sendIntent.setType("text/plain")

                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}



