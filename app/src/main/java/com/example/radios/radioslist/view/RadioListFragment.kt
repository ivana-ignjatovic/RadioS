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
import androidx.navigation.NavController
import com.example.radios.R
import com.example.radios.base.ICoordinator
import com.example.radios.base.data.HttpDataHandler
import com.example.radios.base.data.RadioParser
import com.example.radios.base.data.Result
import com.example.radios.base.model.Radio
import com.example.radios.databinding.ActivityMainBinding
import com.example.radios.radioslist.recycler.RadioRVAdapter
import kotlinx.android.synthetic.main.fragment_radio_list.*


open class RadioListFragment : Fragment() {

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
        return inflater.inflate(R.layout.fragment_radio_list, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getRadios()
    }

    fun setupRecyclerView(radios: List<Radio>) {
        radiolist.adapter = RadioRVAdapter(radios, this, false) { radio ->
            (activity as ICoordinator).showDetailsFragment(radio)

        }
    }

    fun getRadios() {

        val thread = Thread() {
            val response =
                HttpDataHandler.getResponse("http://de1.api.radio-browser.info/json/stations/bycountrycodeexact/RS")
            if (response is Result.Success) {
                Log.d("response", response.data)
                val radios = RadioParser.parse(response.data)

                activity?.runOnUiThread() {
                    setupRecyclerView(radios)
                }
            }
        }
        thread.start()

    }

    fun setAndPlay(url: String) {
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

    fun pause() {

        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
            mediaPlayer.reset()
        }
    }
}