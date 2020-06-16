package com.example.flightmobileapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey

class DatabaseEntities {

    @Entity(tableName = "servers_table")
    data class Server(

        @PrimaryKey
        val id:String,

        val url: String
    )

}