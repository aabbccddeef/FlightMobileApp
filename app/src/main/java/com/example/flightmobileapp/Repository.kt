package com.example.flightmobileapp

import androidx.lifecycle.LiveData
import com.example.flightmobileapp.db.DatabaseEntities
import com.example.flightmobileapp.db.ServersDatabase

class Repository(private val database: ServersDatabase) {

    fun AddServer(server: DatabaseEntities.Server){
        database.serversDao.addServer(server)
    }

    val servers: LiveData<List<DatabaseEntities.Server>> = database.serversDao.getLastFive()

}