package com.example.flightmobileapp

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.flightmobileapp.db.DatabaseEntities
import com.example.flightmobileapp.db.getDatabase
import java.util.*

/**
 * View Model Class of Login fragment
 */
class LoginViewModel(application: Application) : ViewModel() {


    private val database = getDatabase(application)

    private val repository = Repository(database)


    /**
     * save url's from user input as arraylist of String and  bind it to RecyclerView
     */
    //private val _urls = MutableLiveData<ArrayList<String>>()
    private val _servers = repository.servers
    val urls: LiveData<List<String>>
        get() {
            return _servers
        }

    /**
     * add new url after user has submitted
     */
    fun addUrl(url: String) {
        //_urls.value?.add(url)
        val id: UUID = UUID.randomUUID()
        repository.AddServer(DatabaseEntities.Server(id.toString(), url))
    }
    // TODO: Implement the ViewModel
}
