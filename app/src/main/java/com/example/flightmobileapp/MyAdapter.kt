package com.example.flightmobileapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * Adapter to populate the RecyclerView
 */
public class MyAdapter(val listener: Iselected) : RecyclerView.Adapter<MyAdapter.ViewHolder>(){


/*     var urls : List<String> = ArrayList<String>()
       *//* get() {
           // return urls
            TODO()
        }*//*
        set(value) {
            urls = value
            notifyDataSetChanged()
        }*/


    var urls : List<String> = ArrayList<String>()

    var selected: Int = -1

    fun setUrls(urls: ArrayList<String>){
        this.urls = urls
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return urls.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = urls[position].toString()
        if(position == selected){
            holder.row_parent.setBackgroundColor(Color.YELLOW)
        } else {
            holder.row_parent.setBackgroundColor(0)
        }

        holder.row_parent.setOnClickListener(View.OnClickListener {
            selected = position
            notifyDataSetChanged()
            listener.onSelected(urls[position])
        })

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView = itemView.findViewById(R.id.row_text) as TextView
        val row_parent = itemView.findViewById(R.id.row_parent) as View
    }

    interface Iselected{
        fun onSelected(url: String)
    }
}