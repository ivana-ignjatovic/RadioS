package com.example.radios.recycler

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.example.radios.model.Radio
import kotlinx.android.synthetic.main.item_radio.view.*


class RadioRVViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(radio: Radio){
        itemView.TVname.text=radio.name
        itemView.country.text=radio.country
        if(radio.favicon!=""){
            Glide.with(itemView).load(radio.favicon).into(itemView.Radioimage)
        }

    }
}