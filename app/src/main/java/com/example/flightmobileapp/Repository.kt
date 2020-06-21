package com.example.flightmobileapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.flightmobileapp.db.DatabaseEntities
import com.example.flightmobileapp.db.ServersDatabase
import com.example.flightmobileapp.network.ApiService
import com.example.flightmobileapp.network.MoviesList
import com.example.flightmobileapp.network.SERVER_URL
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class Repository(private val database: ServersDatabase) {

    enum class ApiStatus { ERROR, DONE }

    val status = MutableLiveData<ApiStatus>()



/*
    private val _image = MutableLiveData<String>()
    val image: LiveData<String>
        get() = _image
*/

    private val _image = MutableLiveData<MoviesList>()
    val image: LiveData<MoviesList>
        get() = _image

    //  lateinit var image: String

    val urls: LiveData<List<String>> = database.serversDao.getLastFive()

    /**
     * async method - called from GET command , get the picture from server
     */
    suspend fun getImage() {
        withContext(Dispatchers.IO) {
            try {
                var getImageDeffered = ApiService.ServerApi.retrofitService.getImage(/*url*/)
                var getImageWaited = getImageDeffered.await()
                _image.postValue(getImageWaited)
                status.postValue(ApiStatus.DONE)
                var a = 1
            } catch (e: Exception){
                var a = 1
                status.postValue(ApiStatus.ERROR)
            }
        }
    }

    fun addUrlForNetwork(url: String) {
        SERVER_URL = url
    }

    /**
     * bind url that user has entered to DB
     */
    suspend fun AddServer(server: DatabaseEntities.Server) {
        withContext(Dispatchers.IO) {
            database.serversDao.addServer(server)
        }
    }
}