package com.example.horoscopoapli

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.horoscopoapli.R

class HoroscopeAdapter(private val dataSet: List<Horoscope>) :
    RecyclerView.Adapter<HoroscopeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoroscopeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layouthoroscopes, parent, false)

        return HoroscopeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
    override fun onBindViewHolder(holder: HoroscopeViewHolder, position: Int) {
        val horoscope = dataSet[position]
        holder.textView.text = horoscope.name

    }

}

class HoroscopeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val textView: TextView


    init {
        textView = view.findViewById(R.id.nameTextHoros)

    }
}