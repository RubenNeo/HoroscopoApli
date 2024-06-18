package com.example.horoscopoapli.activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.horoscopoapli.R
import com.example.horoscopoapli.adapter.HoroscopeAdapter
import com.example.horoscopoapli.data.Horoscope
import com.example.horoscopoapli.data.HoroscopeProvider

class MainActivity : AppCompatActivity() {


    lateinit var horoscopeList: List<Horoscope>

    lateinit var recyclerView: RecyclerView

    lateinit var adapter: HoroscopeAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        horoscopeList = HoroscopeProvider.findAll()

        recyclerView = findViewById(R.id.recyclerView)


        adapter = HoroscopeAdapter(horoscopeList) { position ->
            navigateToDetail(horoscopeList[position])
        }

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    // la creacion del menu de la carpeta res
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_activity_main, menu)

        val searchViewItem = menu.findItem(R.id.menu_search)
        val searchView = searchViewItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {

                    horoscopeList = HoroscopeProvider.findAll()
                        .filter { getString(it.name).contains(newText, true) }
                    adapter.updateData(horoscopeList)
                }
                return true
            }
        })

        return true
    }


    fun navigateToDetail(horoscope: Horoscope) {
        val intent: Intent = Intent(this, SecondActivity::class.java)
        intent.putExtra(SecondActivity.EXTRA_HOROSCOPE_ID, horoscope.id)
        startActivity(intent)
    }
}



