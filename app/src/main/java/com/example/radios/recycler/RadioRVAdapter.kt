package com.example.radios.recycler

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.radios.R
import com.example.radios.model.Brewery


class RadioRVAdapter(private val brewerys: List<Brewery>) :
    RecyclerView.Adapter<RadioRVViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RadioRVViewHolder =
        RadioRVViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_radio, parent, false)
        )

    override fun onBindViewHolder(holder: RadioRVViewHolder, position: Int) {
        holder.bind(brewerys[position])
    }

    override fun getItemCount() = brewerys.size

}