package com.example.horoscopoapli.activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.example.horoscopoapli.util.InitSession
import com.example.horoscopoapli.R
import com.example.horoscopoapli.data.Horoscope
import com.example.horoscopoapli.data.HoroscopeProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class SecondActivity : AppCompatActivity() {


    companion object {
        const val EXTRA_HOROSCOPE_ID = "List_Horoscope"

    }


    lateinit var horoscope: Horoscope
    var isFavorite = false

    lateinit var dailyText : TextView

    lateinit var Session: InitSession

    lateinit var FavoriteMenuItem: MenuItem

    lateinit var checkCompatibilityButton : FloatingActionButton



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        checkCompatibilityButton = findViewById(R.id.checkCompatibilityButton)


        checkCompatibilityButton.setOnClickListener{
            val intent = Intent (this , compatibilty::class.java)
            startActivity(intent)
        }



        Session = InitSession(this)

        val initPage = findViewById<FloatingActionButton>(R.id.ButtonInit)
        initPage.setOnClickListener() {
            finish()
        }


        val id = intent.getStringExtra(EXTRA_HOROSCOPE_ID)
        horoscope = HoroscopeProvider.findById(id!!)!!

        isFavorite = Session.isFavorite(horoscope.id)

        dailyText = findViewById(R.id.dailyText)


        findViewById<TextView>(R.id.textName).setText(horoscope.name)
        findViewById<ImageView>(R.id.ImageViewH).setImageResource(horoscope.logo)




        supportActionBar?.setTitle(horoscope.name)
        supportActionBar?.setSubtitle(horoscope.description)
        getDailyHoroscope()

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
    fun getDailyHoroscope() {
        // Llamada en hilo secundario
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Declaramos la url
                val url = URL("https://horoscope-app-api.vercel.app/api/v1/get-horoscope/daily?sign=${horoscope.id}&day=TODAY")
                val con = url.openConnection() as HttpsURLConnection
                con.requestMethod = "GET"
                val responseCode = con.responseCode
                Log.i("HTTPS", "Response Code :: $responseCode")

                // Preguntamos si hubo error o no
                if (responseCode == HttpsURLConnection.HTTP_OK) { // Ha ido bien
                    // Metemos el cuerpo de la respuesta en un BurfferedReader
                    val bufferedReader = BufferedReader(InputStreamReader(con.inputStream))
                    var inputLine: String?
                    val response = StringBuffer()
                    while (bufferedReader.readLine().also { inputLine = it } != null) {
                        response.append(inputLine)
                    }
                    bufferedReader.close()

                    // Parsear JSON
                    val json = JSONObject(response.toString())
                    val result =  json.getJSONObject("data").getString("horoscope_data")

                    // Ejecutamos en el hilo principal
                    /*CoroutineScope(Dispatchers.Main).launch {
                    }*/
                    runOnUiThread {
                        dailyText.text = result
                    }

                } else { // Hubo un error
                    Log.w("HTTP", "Response :: Hubo un error")
                }
            } catch (e: Exception) {
                Log.e("HTTP", "Response Error :: ${e.stackTraceToString()}")
            }
        }
    }
}




