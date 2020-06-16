package com.example.flightmobileapp

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.login_fragment.*

/**
 * Fragment of Main Page
 */
class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    /**
     * viewModel object
     */
    private lateinit var viewModel: LoginViewModel

    /**
     * called to inflate the layout of the fragment and initialize GUI on creation of the fragment
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    /**
     * called after the onCreate() method.  used to assign this fragment's View vars
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        // TODO: Use the ViewModel

        val adapter = MyAdapter()
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(context)

        type_url.setOnClickListener(View.OnClickListener {
            var txt = url.text
            viewModel.addUrl(txt.toString())
        })

        viewModel.urls.observe(viewLifecycleOwner, Observer {

        })
    }

}
