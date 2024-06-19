package com.example.horoscopoapli.adapter

import android.provider.ContactsContract.CommonDataKinds.Im
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.core.view.accessibility.AccessibilityViewCommand.SetTextArguments
import androidx.recyclerview.widget.RecyclerView
import com.example.horoscopoapli.R
import com.example.horoscopoapli.data.Horoscope
import com.example.horoscopoapli.util.InitSession

//declarar la funcion Lambda--------------------------------->
class HoroscopeAdapter(
    private var dataSet: List<Horoscope>,
    private val onItemClickListener: (Int) -> Unit
) :
    RecyclerView.Adapter<HoroscopeViewHolder>() {
    fun updateData(newDataSet: List<Horoscope>) {
        dataSet = newDataSet
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoroscopeViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layouthoroscopes, parent, false)

        return HoroscopeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: HoroscopeViewHolder, position: Int) {
        val horoscope = dataSet[position]
        holder.render(horoscope)
        holder.itemView.setOnClickListener {
            onItemClickListener(position)
        }

    }

}

class HoroscopeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val textView: TextView
    val NameDc: TextView
    val ImViewLogo: ImageView
    val FavoriteIcon: ImageView

    init {
        textView = view.findViewById(R.id.nameHoros)
        NameDc = view.findViewById(R.id.NameDc)
        ImViewLogo = view.findViewById(R.id.ImViewLogo)
        FavoriteIcon = view.findViewById(R.id.FavoriteIcon)


    }

    fun render(horoscope: Horoscope) {

        textView.setText(horoscope.name)
        NameDc.setText(horoscope.description)
        ImViewLogo.setImageResource(horoscope.logo)


        val context = itemView.context
        var isFavorite = InitSession(context).isFavorite(horoscope.id)
        if (isFavorite) {
            FavoriteIcon.visibility = View.VISIBLE
        } else {
            FavoriteIcon.visibility = View.GONE
        }

    }

}

