package com.example.radios.recycler

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.radios.R
import com.example.radios.model.Radio


class RadioRVAdapter(private val radios: List<Radio>) :
    RecyclerView.Adapter<RadioRVViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RadioRVViewHolder =
        RadioRVViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_radio, parent, false)
        )

    override fun onBindViewHolder(holder: RadioRVViewHolder, position: Int) {
        holder.bind(radios[position])
    }

    override fun getItemCount() = radios.size

}