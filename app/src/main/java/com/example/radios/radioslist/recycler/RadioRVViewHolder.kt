package com.example.radios.radioslist.recycler

import android.annotation.SuppressLint
import android.media.AudioManager
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.media2.player.MediaPlayer
import androidx.recyclerview.widget.RecyclerView
import com.example.radios.R
import com.example.radios.base.DBHelper
import com.example.radios.base.MainActivity
import com.example.radios.base.model.Radio
import com.example.radios.base.model.RadiosUsers
import com.example.radios.radioslist.view.FavoritesFragment
import com.example.radios.radioslist.view.RadioListFragment
import com.example.radios.registration.LogInFragment
import kotlinx.android.synthetic.main.fragment_radio_list.view.*
import kotlinx.android.synthetic.main.item_radio.view.*


class RadioRVViewHolder(view: View, frag: Fragment) : RecyclerView.ViewHolder(view) {
    val star = R.drawable.ic_star22
    val played = R.drawable.ic_played
    var myFrag = frag


    @SuppressLint("ResourceAsColor")
    fun bind(radio: Radio, onItemCLicked: (String) -> Unit) {

        itemView.TVname.text = radio.name
        itemView.setOnClickListener { onItemCLicked.invoke(radio.id) }
        val audioUrl = radio.stream

        itemView.btnplay.setOnClickListener() {
            try {
                if (myFrag is RadioListFragment) {
                    (myFrag as RadioListFragment).setAndPlay(audioUrl)
                    itemView.btnplay.setBackgroundResource(played)
                } else if (myFrag is FavoritesFragment) {
                    (myFrag as FavoritesFragment).setAndPlay(audioUrl)
                    itemView.btnplay.setBackgroundResource(played)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        itemView.btnpause.setOnClickListener() {
            if (myFrag is RadioListFragment) {
                (myFrag as RadioListFragment).pause()
                itemView.btnplay.setBackgroundResource(R.drawable.ic_play)
            } else if (myFrag is FavoritesFragment) {
                (myFrag as FavoritesFragment).pause()
                itemView.btnplay.setBackgroundResource(R.drawable.ic_play)
            }
        }

        itemView.btnSave.setOnClickListener() {
            Log.d("ID:", R.layout.item_radio.toString())
            var username = LogInFragment.username.un
            var radioId = radio.id
            val rusr = RadiosUsers(username, radioId)
            val db = DBHelper(it.context)
            val fav = db.getFavoriteById(username, radioId)
            if (fav.radios == radioId) {
                Toast.makeText(it.context, "Ovaj radio je vec sacuvan!", Toast.LENGTH_LONG).show()
            } else {
                val favorite = db.insertFavorite(rusr)
                itemView.btnSave.setBackgroundResource(star)
                Toast.makeText(it.context, "Uspesno ste uneli favorita!", Toast.LENGTH_LONG).show()
            }
        }

        itemView.btnDelete.setOnClickListener() {
            var username = LogInFragment.username.un
            var radioId = radio.id
            val rusr = RadiosUsers(username, radioId)
            val db = DBHelper(it.context)
            db.deleteFavorite(username, radioId)

            Toast.makeText(it.context, "Uspesno ste izbrisali sacuvani radio", Toast.LENGTH_LONG)
                .show()
            (myFrag as FavoritesFragment).getFavorites()
        }
    }


}



