package com.example.radios.radioslist.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.radios.R
import com.example.radios.base.model.Radio


class RadioRVAdapter(private val radios: List<Radio>, private val onItemClicked:(String)->Unit) :
    RecyclerView.Adapter<RadioRVViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RadioRVViewHolder =
        RadioRVViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_radio, parent, false)
        )

    override fun onBindViewHolder(holder: RadioRVViewHolder, position: Int) {
        holder.bind(radios[position], onItemClicked)
    }


    override fun getItemCount() = radios.size
}



