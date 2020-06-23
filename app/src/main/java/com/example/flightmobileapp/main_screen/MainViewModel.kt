package com.example.flightmobileapp.main_screen

import android.app.Application
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.flightmobileapp.Repository
import com.example.flightmobileapp.db.getDatabase
import com.example.flightmobileapp.network.MyBitmap
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

  //  val bitmap = MutableLiveData<Bitmap>()

    val bitmap: LiveData<Bitmap> =
        Transformations.map(repository.image) {
            it.asBitmap()
        }


    val image: LiveData<Bitmap>
        get() {
            return bitmap
        }

/*    private fun initializeImage(): MutableLiveData<Bitmap> {
        var imageData = _image.value

        val imageBytes = Base64.decode(imageData, 0)
        val image = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)

        bitmap.postValue(image)
        return bitmap
    }*/

    fun connect() {
        coroutineScope.launch {
            repository.getImage()

          //  initializeImage()
        }
    }
}

private fun String.asBitmap(): Bitmap {
    val imageBytes = Base64.decode(this, 0)
    val image = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
    return image
}
