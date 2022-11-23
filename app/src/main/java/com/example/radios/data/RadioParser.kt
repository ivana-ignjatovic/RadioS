package com.example.radios.data

import android.util.Log
import com.example.radios.model.Radio
import org.json.JSONArray
import org.json.JSONException

object RadioParser {
    private const val TAG = "RadiosParser"
    fun parse(json :String): List<Radio>{

        val radios = mutableListOf<Radio>()
        try{


            val radiosJsonArray = JSONArray(json)

            for (i in 0 until radiosJsonArray.length()){
                val jsonRadio = radiosJsonArray.getJSONObject(i)
                Log.d("Json tag",jsonRadio.toString())
               // val id = jsonRadio.getString("stationuuid")
                val name = jsonRadio.getString("name")
                val country = jsonRadio.getString("country")
                val favicon = jsonRadio.getString("favicon")
                val radio = Radio(name,country,favicon)
                radios.add(radio)
            }
        }catch(e:JSONException){
            Log.e(TAG, e.toString())
        }

        return radios
    }
}