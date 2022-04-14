package com.example.rickandmortylist.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RemoteViews
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortylist.R

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemIcon = itemView.findViewById<ImageView>(R.id.listImageView)
        var itemTitle = itemView.findViewById<ImageView>(R.id.listTitle)
        var itemSubtitle = itemView.findViewById<ImageView>(R.id.listSubtitle)
    }

}