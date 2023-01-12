package com.example.radios.radioslist.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.radios.R
import com.example.radios.base.model.Radio
import com.example.radios.radioslist.view.RadioListFragment
import kotlinx.android.synthetic.main.item_radio.view.*


class RadioRVAdapter(
    private val radios: List<Radio>,
    private val frag: Fragment,
    private val favFrag: Boolean,
    private val onItemClicked: (String) -> Unit
) :
    RecyclerView.Adapter<RadioRVViewHolder>() {
    lateinit var myFrag: Fragment
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RadioRVViewHolder {
        this.myFrag = frag
        if (favFrag) {
            return RadioRVViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_favorite, parent, false),
                this.myFrag

            )
        }

        return RadioRVViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_radio, parent, false),
            this.myFrag

        )
    }

    override fun onBindViewHolder(holder: RadioRVViewHolder, position: Int) {
        holder.bind(radios[position], onItemClicked)

    }

    override fun getItemCount() = radios.size
}



