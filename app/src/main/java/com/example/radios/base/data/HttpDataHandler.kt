package com.example.radios.base.data

import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
sealed class  Result<out T>{
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}

object HttpDataHandler {
   // private const val RADIO_TOKEN ="11d3b0ca91msh58d60edd5ae7edep16aed7jsnc68288202283"
    fun getResponse(urlString: String): Result<String> {
        try{
            val url = URL(urlString)
            val urlConnection = url.openConnection() as HttpURLConnection
            
           // urlConnection.setRequestProperty("Authorazation","Radio $RADIO_TOKEN")
            
            if(urlConnection.responseCode==200){
                val inputStream = BufferedInputStream(urlConnection.inputStream)
                val reader = BufferedReader(InputStreamReader(inputStream))
                
                val builder = StringBuilder()
                var line = reader.readLine()
                
                while(line!=null){
                    builder.append(line)
                    line = reader.readLine()
                }
                
                val string = builder.toString()
                urlConnection.disconnect()
                
                return Result.Success(string)
                
            }
            return Result.Error(Exception("Error occured  - Response code: ${urlConnection.responseCode}"))
        }
        catch (e: Exception){
            return Result.Error(e)
        }
    }
}