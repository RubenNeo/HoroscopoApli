package com.example.horoscopoapli

import android.provider.ContactsContract.CommonDataKinds.Im
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.accessibility.AccessibilityViewCommand.SetTextArguments
import androidx.recyclerview.widget.RecyclerView
import com.example.horoscopoapli.R
//declarar la funcion Lambda--------------------------------->
class HoroscopeAdapter(private val dataSet: List<Horoscope>, private val OnItemClickListener :(Int) -> Unit) :
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
        holder.render(horoscope)
        holder.itemView.setOnClickListener {
            OnItemClickListener(position)
        }
// la funcion Lamda decimos que al hacer click en esa posicion no mande a la misma.
    }

}

class HoroscopeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val textView: TextView
    val NameDc: TextView
    val ImViewLogo : ImageView

    init {
        textView = view.findViewById(R.id.nameHoros)
        NameDc = view.findViewById(R.id.NameDc)
        ImViewLogo= view.findViewById(R.id.ImViewLogo)

    }
 fun render(horoscope: Horoscope){

     textView.setText(horoscope.name)
     NameDc.setText(horoscope.description)
     ImViewLogo.setImageResource(horoscope.logo)


 }

}