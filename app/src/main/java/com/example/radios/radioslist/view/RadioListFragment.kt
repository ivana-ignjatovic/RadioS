package com.example.radios.radioslist.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.radios.R
import com.example.radios.base.ICoordinator
import com.example.radios.base.MainActivity
import com.example.radios.base.data.HttpDataHandler
import com.example.radios.base.data.RadioParser
import com.example.radios.base.data.Result
import com.example.radios.base.model.Radio
import com.example.radios.favorites.FavoritesFragment
import com.example.radios.radioslist.recycler.RadioRVAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_radio_list.*


open class RadioListFragment : Fragment() {

  var bottomNav : BottomNavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // bottomNav = view.findViewById(R.id.bottomNav) as BottomNavigationView


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_radio_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getRadios()
        //(activity as MainActivity).showFragment(RadioListFragment(), false)

        bottomNav = view.findViewById(R.id.bottomNav) as BottomNavigationView
        //bottomNav.findViewById(R.id.bottomNav)as BottomNavigationView
        bottomNav!!.setOnNavigationItemReselectedListener {
            when (it.itemId) {
                R.id.home -> {
                    (activity as MainActivity).showFragment(RadioListFragment(), false)
                    return@setOnNavigationItemReselectedListener
                }

                R.id.settings -> {
                    (activity as MainActivity).showFragment(FavoritesFragment(), false)
                    return@setOnNavigationItemReselectedListener

                }
            }
        }
     }

     fun setupRecyclerView(radios: List<Radio>){
        radiolist.adapter= RadioRVAdapter(radios){radio ->
            (activity as ICoordinator).showDetailsFragment(radio)
        }
        radiolist.adapter= RadioRVAdapter(radios){radio ->
            (activity as ICoordinator).showDetailsFragment(radio)
        }

    }

     fun getRadios(){

        val thread = Thread(){
            val response = HttpDataHandler.getResponse("http://de1.api.radio-browser.info/json/stations/bycountrycodeexact/RS")
            if (response is Result.Success){
                Log.d("response", response.data)
                val radios = RadioParser.parse(response.data)

                activity?.runOnUiThread(){
                    setupRecyclerView(radios)
                }
                //Log.d("UserLog","Radios receivde ${radios}")
            }

        }
       thread.start()
    }
}