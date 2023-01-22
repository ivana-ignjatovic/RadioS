package com.example.radios.radioslist.view

import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.radios.R
import com.example.radios.base.DBHelper
import com.example.radios.base.ICoordinator
import com.example.radios.base.data.HttpDataHandler
import com.example.radios.base.data.RadioParser
import com.example.radios.base.data.Result
import com.example.radios.base.model.Radio
import com.example.radios.base.model.RadiosUsers
import com.example.radios.registration.LogInFragment
import com.example.radios.radioslist.recycler.RadioRVAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_favorites.*

class FavoritesFragment : Fragment() {
    lateinit var mediaPlayer: android.media.MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mediaPlayer = android.media.MediaPlayer()
        mediaPlayer!!.setAudioStreamType(AudioManager.STREAM_MUSIC)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getFavorites()


    }

    fun setupRecycleView(radios: List<Radio>){

        favoriteslist.adapter= RadioRVAdapter(radios, this,true){radio ->
            (activity as ICoordinator).showDetailsFragment(radio)
            //(activity as MainActivity).refreshFragment(this.context,FavoritesFragment())
        }
        (favoriteslist.adapter as RadioRVAdapter).notifyDataSetChanged()
    //    btnSave.setBackgroundResource(R.drawable.ic_star)

    }

     fun getFavorites() {
        val db = DBHelper(requireContext())
        var favbyyser: List<RadiosUsers> = db.getALLFavorites()
       favbyyser = favbyyser.filter { it.username == LogInFragment.username.un }

        val thread = Thread(){
            val response = HttpDataHandler.getResponse("http://de1.api.radio-browser.info/json/stations/bycountrycodeexact/RS")
            if (response is Result.Success){
                Log.d("response", response.data)
                var radios = RadioParser.parse(response.data)
                    val favId:List<String> = favbyyser.map { it.radios}
                   radios= radios.filter { favId.contains(it.id)}

                activity?.runOnUiThread(){
                    setupRecycleView(radios)
                }
            }

        }
        thread.start()
    }
    fun setAndPlay(url:String){
        pause()
        mediaPlayer = android.media.MediaPlayer()
        mediaPlayer!!.setAudioStreamType(AudioManager.STREAM_MUSIC)
        mediaPlayer.setOnPreparedListener(object : MediaPlayer.OnPreparedListener {
            override fun onPrepared(mp: MediaPlayer) {
                mp.start()
            }
        })
        mediaPlayer.setDataSource(url)
        mediaPlayer.prepareAsync()
    }
    fun pause(){
        if(mediaPlayer.isPlaying){
            mediaPlayer.pause()
            mediaPlayer.reset()
            mediaPlayer.release()
        }

    }


    }
