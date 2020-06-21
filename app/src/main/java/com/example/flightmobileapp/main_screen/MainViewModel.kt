package com.example.flightmobileapp.main_screen

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.flightmobileapp.Repository
import com.example.flightmobileapp.db.getDatabase
import com.example.flightmobileapp.network.MoviesList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : ViewModel() {

    private val database = getDatabase(application)
    private val repository = Repository(database)

    private val _status = repository.status
    val status: LiveData<Repository.ApiStatus>
        get() {
            return _status
        }

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _image = repository.image
    /*   val image: LiveData<String>
           get() {
               return _image
           }*/

    val image: LiveData<MoviesList>
        get() {
            return _image
        }

    fun connect() {
        coroutineScope.launch {
            repository.getImage()
        }
    }
}
