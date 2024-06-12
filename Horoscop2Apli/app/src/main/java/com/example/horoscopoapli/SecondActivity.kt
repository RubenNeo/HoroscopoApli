package com.example.horoscopoapli

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)


        val id = intent.getStringExtra("Id Horoscope")
        val name = intent.getIntExtra("Name Horoscope", -1)
        val logo = intent.getIntExtra("logo Horoscope", -1)


        findViewById<ImageView>(R.id.ImageViewH).setImageResource(logo)
        findViewById<TextView>(R.id.textName).setText(name)

    }
}