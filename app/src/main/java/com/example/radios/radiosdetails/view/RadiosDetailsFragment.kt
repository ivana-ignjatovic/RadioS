package com.example.radios.radiosdetails.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.radios.R
import com.example.radios.base.data.HttpDataHandler
import com.example.radios.base.data.RadioParser
import com.example.radios.base.data.Result
import com.example.radios.base.model.Radio
import kotlinx.android.synthetic.main.fragment_radios_details.*

class RadiosDetails : Fragment() {


    var radioId: String = ""

    companion object {
        private const val RADIO_ID_KEY = "stationuuid"
        fun newInstance(radioId: String): RadiosDetails {
            return RadiosDetails().apply {
                arguments = Bundle().apply { putString(RADIO_ID_KEY, radioId) }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        radioId = arguments?.getString(RADIO_ID_KEY) ?: radioId
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_radios_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getRadio()

    }

    private fun setupView(radio: Radio) {

        name.text = radio.name
        country.text = radio.countrycode + " " + radio.country
        if (radio.language != null) {
            language.text = radio.languagecode + " " + radio.language
        } else {
            language.text = "unknow"
        }
        if (radio.favicon != "") {
            Glide.with(this).load(radio.favicon).into(favicon)
        }
        votes.text = radio.votes.toString()
       // homepage.setOnClickListener()
        var url = radio.homepage
        val i= Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        homepage.setOnClickListener(){
            startActivity(i)
        }
    }

    private fun getRadio() {
        Log.d("Radioi id ", radioId)
        val thread = Thread() {
            val response =
                HttpDataHandler.getResponse("http://de1.api.radio-browser.info/json/stations/byuuid/" + radioId)
            if (response is Result.Success) {

                val radio = RadioParser.parse2(response.data)
                //println("Ikaa"+radio)
                //   for(i in radios.indices){
                ///     if(radios.get(i).id==radioId)
                //}

                activity?.runOnUiThread() {
                    setupView(radio)
                }
            }
        }.start()


    }
}



