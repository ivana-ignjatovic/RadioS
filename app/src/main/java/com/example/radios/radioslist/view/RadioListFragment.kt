package com.example.radios.radioslist.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.radios.R
import com.example.radios.base.ICoordinator
import com.example.radios.base.data.HttpDataHandler
import com.example.radios.base.data.RadioParser
import com.example.radios.base.data.Result
import com.example.radios.base.model.Radio
import com.example.radios.radioslist.recycler.RadioRVAdapter
import com.example.radios.radioslist.viewmodel.RadioListViewModel
import kotlinx.android.synthetic.main.fragment_radio_list.*
class RadioListFragment : Fragment() {
lateinit var  viewModel : RadioListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //viewModel = ViewModelProvider(this).get(RadioListViewModel::class.java)
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

     }
   /* private fun bindFromViewModel() {

        viewModel.state.observe(viewLifecycleOwner) { state ->

            progressBar.isVisible = state is Processing

            when (state) {
                //is DataReceived -> setUpRecyclerView(state.breweries)
                is ErrorReceived -> showError(state.message)
            }
        }
    }*/
    private fun setupRecyclerView(radios: List<Radio>){
        radiolist.adapter= RadioRVAdapter(radios){
            (activity as ICoordinator).showDetailsFragment()
        }


    }

    private fun getRadios(){

        val thread = Thread(){
            val response = HttpDataHandler.getResponse("http://de1.api.radio-browser.info/json/stations/bycountrycodeexact/RS")
            if (response is Result.Success){
                //Log.d("response", response.data)
                val radios = RadioParser.parse(response.data)

                activity?.runOnUiThread(){
                    setupRecyclerView(radios)
                }


                Log.d("UserLog","Radios receivde ${radios}")
            }

        }
       thread.start()
    }

}