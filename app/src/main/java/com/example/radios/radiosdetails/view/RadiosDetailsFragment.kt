 package com.example.radios.radiosdetails.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.radios.R
import com.example.radios.base.data.HttpDataHandler
import com.example.radios.base.data.RadioParser
import com.example.radios.base.data.Result
import com.example.radios.base.model.Radio
import kotlinx.android.synthetic.main.fragment_radios_details.*

 // TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class RadiosDetails : Fragment() {

    var radioId : Int = -1
    companion object{
        private const val RADIO_ID_KEY = "RADIO_ID"
        fun newInstance(radioId:Int):RadiosDetails{
            return RadiosDetails().apply {
                arguments = Bundle().apply { putInt(RADIO_ID_KEY,radioId) }
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        radioId = arguments?.getInt(RADIO_ID_KEY)?: -1
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

    private fun setupView (radio : Radio){
        name.text=radio.name
        country.text=radio.country
//        countrycode.text=radio.country_code
//        state2.text=radio.state
//        language2.text=radio.language
//        languagecode2.text=radio.language_code
//        votes2.text=radio.votes.toString()
//        val view = ServiceView(requireContext())
//        view.bind(radio)

        }
    private fun getRadio(){

        val thread = Thread(){
            val response = HttpDataHandler.getResponse("http://de1.api.radio-browser.info/json/stations/byuuid/37cd2212-ac24-4002-802d-f6c05c488708")
            if (response is Result.Success){
                //Log.d("response", response.data)
                val radio = RadioParser.parse2(response.data)

                activity?.runOnUiThread(){
                    setupView(radio)
                }


                Log.d("UserLog","Radios receivde ${radio}")
            }

        }
        thread.start()
    }
    }


