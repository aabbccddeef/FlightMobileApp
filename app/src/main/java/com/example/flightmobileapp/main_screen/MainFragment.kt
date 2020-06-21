package com.example.flightmobileapp.main_screen

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.flightmobileapp.R
import com.example.flightmobileapp.Repository


class MainFragment : Fragment() {

    companion object {
        fun newInstance() =
            MainFragment()
    }

    private val viewModel: MainViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProviders.of(this,
            MainViewModelFactory(activity.application)
        )
            .get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.image.observe(viewLifecycleOwner, Observer {

        })

        viewModel.status.observe(viewLifecycleOwner, Observer {
            if(it == Repository.ApiStatus.ERROR){
                // TODO go to login
                Toast.makeText(context, "error", Toast.LENGTH_LONG).show()
            }
        })


        var timer = object : CountDownTimer(5000, 100) {
            override fun onTick(millisUntilFinished: Long) {
                viewModel.connect()
            }

            override fun onFinish() {
                try {
                } catch (e: Exception) {
                    Log.e("Error", "Error: $e")
                }
            }
        }.start()


    }

}
