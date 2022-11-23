package com.example.radios
import  com.example.radios.model.Radio

sealed class RadioListViewState {

    object Processing: RadioListViewState()
    data class DataReceived(val radios: List<Radio>) : RadioListViewState()
    data class ErrorReceived(val message: String) : RadioListViewState()
}