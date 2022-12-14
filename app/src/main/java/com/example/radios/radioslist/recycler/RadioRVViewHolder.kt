package com.example.radios.radioslist.recycler

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.radios.base.DBHelper
import com.example.radios.base.model.Radio
import com.example.radios.base.model.RadiosUsers
import com.example.radios.fragments.LogInFragment
import kotlinx.android.synthetic.main.item_radio.view.*

class RadioRVViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    object radioID {
        var ri  = listOf("")
    }
    fun bind(radio: Radio, onItemCLicked:(String)-> Unit) {


        var mediaPlayer: android.media.MediaPlayer
        mediaPlayer = android.media.MediaPlayer()
        itemView.TVname.text = radio.name
        itemView.country.text = radio.country
        itemView.setOnClickListener { onItemCLicked.invoke(radio.id) }
//        if (radio.favicon != "") {
        // Glide.with(itemView).load(radio.favicon).into(itemView.radiolist.favicon)
//        }
        val audioUrl=radio.stream
        itemView.btnplay.setOnClickListener() {


            mediaPlayer!!.setAudioStreamType(android.media.AudioManager.STREAM_MUSIC)

            try{
                mediaPlayer!!.setDataSource(audioUrl)
                mediaPlayer!!.prepare()
                mediaPlayer!!.start()
            }catch (e:Exception){
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
        itemView.btnSave.setOnClickListener(){
            var username = LogInFragment.username.un
            var radioId = radio.id
            val rusr = RadiosUsers (username,radioId)
            radioID.ri+=radioId
            val db = DBHelper(it.context)
            val all : List<RadiosUsers> = db.getALLFavorites()
            val favorite = db.insertFavorite(rusr)

            Log.d("Zvezda",all.get(0).radios + all.get(0).username)
        }

    }



}