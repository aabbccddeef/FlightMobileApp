package com.example.flightmobileapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {


    private val _urls = MutableLiveData<ArrayList<String>>()
    val urls: LiveData<ArrayList<String>>
        get() {
            return _urls
        }

    fun addUrl(url: String) {
        _urls.value?.add(url)
    }
    // TODO: Implement the ViewModel
}
