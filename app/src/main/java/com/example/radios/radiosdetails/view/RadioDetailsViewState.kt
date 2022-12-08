package com.example.radios.radiosdetails.view

import com.example.radios.base.model.Radio

sealed class RadioDetailsViewState {
    object Proccessing : RadioDetailsViewState()
    data class DataReceived(val radios: List<Radio>) : RadioDetailsViewState()
    data class ErrorReceived(val message: String) : RadioDetailsViewState()
}
