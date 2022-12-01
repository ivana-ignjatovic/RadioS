package com.example.radios.radioslist.view
import  com.example.radios.base.model.Radio

sealed class RadioListViewState {

    object Processing: RadioListViewState()
    data class DataReceived(val radios: List<Radio>) : RadioListViewState()
    data class ErrorReceived(val message: String) : RadioListViewState()
}