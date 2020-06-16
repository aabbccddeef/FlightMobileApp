package com.example.flightmobileapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * View Model Class of Login fragment
 */
class LoginViewModel : ViewModel() {


    /**
     * save url's from user input as arraylist of String and  bind it to RecyclerView
     */
    private val _urls = MutableLiveData<ArrayList<String>>()
    val urls: LiveData<ArrayList<String>>
        get() {
            return _urls
        }

    /**
     * add new url after user has submitted
     */
    fun addUrl(url: String) {
        _urls.value?.add(url)
    }
    // TODO: Implement the ViewModel
}
