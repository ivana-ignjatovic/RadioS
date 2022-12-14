package com.example.radios.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.radios.R
import com.example.radios.radioslist.view.RadioListFragment

class Favorites : RadioListFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //if()
    }
    /*public  fun getFavorites(){

        val thread = Thread(){
            val response = HttpDataHandler.getResponse("http://de1.api.radio-browser.info/json/stations/bycountrycodeexact/RS")
            if (response is Result.Success){
                Log.d("response", response.data)
                val radios = RadioParser.parse(response.data)
                val radio = radios.filter { it.name==RadioRVViewHolder.radioID.ri.toString() }
                activity?.runOnUiThread(){
                   // setupRecyclerView(radios)
                }
                //Log.d("UserLog","Radios receivde ${radios}")
            }

        }
        thread.start()
    }
*/
}