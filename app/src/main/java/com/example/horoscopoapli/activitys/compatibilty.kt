package com.example.horoscopoapli.activitys

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isEmpty
import com.example.horoscopoapli.R
import com.example.horoscopoapli.data.HoroscopeProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton

class compatibilty : AppCompatActivity() {


    lateinit var spinnerHoroscope1: Spinner
    lateinit var spinnerHoroscope2: Spinner
    lateinit var calculateCompatibility: Button
    lateinit var progressDialog: ProgressDialog
    lateinit var imageCompatibility : ImageView
    lateinit var textCompatibility : TextView
    lateinit var backButton : ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compatibilty)


        spinnerHoroscope1 = findViewById(R.id.spinnerHoroscope1)
        spinnerHoroscope2 = findViewById(R.id.spinnerHoroscope2)
        calculateCompatibility = findViewById(R.id.calculateCompatibility)
        imageCompatibility = findViewById(R.id.imageCompatibility)
        textCompatibility = findViewById(R.id.textCompatibility)
        backButton = findViewById(R.id.backButton)

        backButton.setOnClickListener{
            val intent = Intent (this , SecondActivity::class.java)
           finish()
        }


        SetupSpinners()

        calculateCompatibility.setOnClickListener {

            val horoscope1 = spinnerHoroscope1.selectedItem.toString()
            val horoscope2 = spinnerHoroscope2.selectedItem.toString()

            if (horoscope1.isEmpty() || horoscope2.isEmpty()) {
                Log.i("CompatibilityActivity", "Error, please select both horoscopes")
                return@setOnClickListener


            }
            showProgessDialog()
            Handler(Looper.getMainLooper()).postDelayed({
                val compatibilty = calculateCompatibility(horoscope1, horoscope2)
                dismissProgressDialog()
                if(compatibilty>50) {
                    imageCompatibility.setImageResource(R.drawable.highcompatibility)
                    textCompatibility.setText("congratulations, you are compatible")

                }else{
                    imageCompatibility.setImageResource(R.drawable.heartbreak)
                    textCompatibility.setText( "I'm sorry, you are incompatible")


                }
                Toast.makeText(this, "Compatibility: $compatibilty%", Toast.LENGTH_SHORT).show()
            }, 2000)


        }
    }





    private fun SetupSpinners() {
        val horoscopesSigns = HoroscopeProvider.horoscopeList.map { getString(it.name) }

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, horoscopesSigns)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerHoroscope1.adapter = adapter
        spinnerHoroscope2.adapter = adapter


    }

    private fun showProgessDialog() {

        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Calculating Compatibility")
        progressDialog.setCancelable(false)
        progressDialog.show()


    }
    private fun dismissProgressDialog(){

        if(::progressDialog.isInitialized && progressDialog.isShowing){
            progressDialog.dismiss()
        }


    }

    private fun calculateCompatibility(horoscope1:String, horoscope2:String):Int{
        return (0..100).random()
    }

}


