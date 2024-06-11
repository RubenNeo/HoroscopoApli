package com.example.horoscopoapli

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.horoscopoapli.Horoscope
import com.example.horoscopoapli.HoroscopeAdapter
import com.example.horoscopoapli.R

class MainActivity : AppCompatActivity() {

    val horoscopeList: List<Horoscope> = listOf(
        Horoscope("aries", "Aries", R.drawable.ic_aries),
        Horoscope("Tauro", "Taurus", 0),
        Horoscope("Geminis", "Gemini", 0),
        Horoscope("Cancer", "Cancer", 0),
        Horoscope("Leo", "Leo", 0),
        Horoscope("Virgo", "Virgo", 0),
        Horoscope("Libra", "Libra", 0),
        Horoscope("Scorpio", "Scorpio", 0),
        Horoscope("Sagittarius", "Sagittarius", 0),
        Horoscope("Capricorn", "Capricorn", 0),
        Horoscope("Aquarius", "Aquarius", 0),
        Horoscope("Piscis", "Piscis", 0)

    )

    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)

        val adapter = HoroscopeAdapter(horoscopeList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(this, 2 )

    }
}
