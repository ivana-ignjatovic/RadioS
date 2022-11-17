package com.example.radios.recycler

import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.radios.model.Brewery
import kotlinx.android.synthetic.main.item_radio.view.*


class RadioRVViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(brewery:Brewery){
        itemView.TVname.text=brewery.name
    }
}