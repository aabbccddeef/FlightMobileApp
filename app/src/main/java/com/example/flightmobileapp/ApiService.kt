package com.example.flightmobileapp

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST

private const val BASE_URL = "https://localhost:44383/"

/**
 * set connection infrastructure to server with REST API
 */
class ApiService {
    /**
     * interface with REST api's
     */
    interface IApiService{
        @GET(BASE_URL + "/api/screenshot")
        fun getImage(): Deferred<String>

       /* @POST(BASE_URL)
        fun connect(): Deferred<String>*/
    }

    object ServerApi{
        private val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        private val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(BASE_URL)
            .build()

        val retrofitService : IApiService by lazy {
            retrofit.create(IApiService::class.java)
        }
    }
}


