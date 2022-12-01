package com.example.radios.radioslist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.radios.radioslist.view.RadioListViewState


class RadioListViewModel : ViewModel() {

    private val _state = MutableLiveData<RadioListViewState>()
    val state: LiveData<RadioListViewState>
        get() = _state

    init {
        //_state.value = RadioListViewState.DataReceived(MockDataProvider.getRadioList())
    }
}