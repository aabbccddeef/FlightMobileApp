package com.example.flightmobileapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.flightmobileapp.db.DatabaseEntities
import com.example.flightmobileapp.db.ServersDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository(private val database: ServersDatabase) {

    suspend fun AddServer(server: DatabaseEntities.Server){
        withContext(Dispatchers.IO) {
            database.serversDao.addServer(server)
        }
    }

    private val _image = MutableLiveData<String>()
    val image: LiveData<String>
        get() = _image


  //  lateinit var image: String

    val urls: LiveData<List<String>> = database.serversDao.getLastFive()

    suspend fun getImage(){
        withContext(Dispatchers.IO) {
            val getImageDeffered = ApiService.ServerApi.retrofitService.getImage()
            _image.value = getImageDeffered.await()
        }
    }

}