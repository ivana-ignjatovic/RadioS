package com.example.radios.base.data

import android.util.Log
import com.example.radios.base.model.Radio
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
                //val favicon = jsonRadio.getString("favicon")
                val stream = jsonRadio.getString("url")
                val radio = Radio(name,country,stream)
                radios.add(radio)
            }
        }catch(e:JSONException){
            Log.e(TAG, e.toString())
        }

        return radios
    }
}