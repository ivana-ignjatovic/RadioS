package com.example.radios.radioslist.recycler

import android.annotation.SuppressLint
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.findFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.radios.R
import com.example.radios.base.DBHelper
import com.example.radios.base.MainActivity
import com.example.radios.base.model.Radio
import com.example.radios.base.model.RadiosUsers
import com.example.radios.favorites.FavoritesFragment
import com.example.radios.fragments.LogInFragment
import com.example.radios.radioslist.view.RadioListFragment
import kotlinx.android.synthetic.main.item_radio.view.*


class RadioRVViewHolder(view: View, frag : Fragment) : RecyclerView.ViewHolder(view) {
    val star = R.drawable.ic_star22
    var myFrag = frag
    var clicked = false
    object radioID {
        var ri  = listOf("")
    }
    @SuppressLint("ResourceAsColor")
    fun bind(radio: Radio, onItemCLicked:(String)-> Unit) {


        var mediaPlayer: android.media.MediaPlayer
        mediaPlayer = android.media.MediaPlayer()
        itemView.TVname.text = radio.name
        itemView.setOnClickListener { onItemCLicked.invoke(radio.id) }
        val audioUrl=radio.stream

        itemView.btnplay.setOnClickListener() {
            mediaPlayer!!.setAudioStreamType(android.media.AudioManager.STREAM_MUSIC)

            try{
                mediaPlayer!!.setDataSource(audioUrl)
                mediaPlayer!!.prepare()
                mediaPlayer!!.start()
            }
            catch (e:Exception){
                e.printStackTrace()
            }
        }
        itemView.btnpause.setOnClickListener(){
            if(mediaPlayer.isPlaying){
                mediaPlayer.stop()
                mediaPlayer.reset()
                mediaPlayer.release()
            }
        }
        if(itemView.id==R.layout.item_radio){
            itemView.btnSave.setOnClickListener(){
                var username = LogInFragment.username.un
                var radioId = radio.id
                val rusr = RadiosUsers (username,radioId)
                val db = DBHelper(it.context)
                val fav = db.getFavoriteById(username,radioId)
                if(fav.radios==radioId){
                    Toast.makeText(it.context, "Ovaj radio je vec sacuvan!", Toast.LENGTH_LONG).show()
                }
                else{
                    val favorite = db.insertFavorite(rusr)
                    itemView.btnSave.setBackgroundResource(star)
                    Toast.makeText(it.context, "Uspesno ste uneli favorita!", Toast.LENGTH_LONG).show()
                }
            }

        }

      itemView.btnDelete.setOnClickListener(){
          var username = LogInFragment.username.un
          var radioId = radio.id
          val rusr = RadiosUsers (username,radioId)
          val db = DBHelper(it.context)
         db.deleteFavorite(username,radioId)
         // var frag : FragmentManager = MainActivity.supportFragmentManager


         Toast.makeText(it.context, "Uspesno ste izbrisali sacuvani radio", Toast.LENGTH_LONG).show()
         // FavoritesFragment().refreshFragment(it.context)
          (myFrag as FavoritesFragment).getFavorites()
          }

      }

    }



