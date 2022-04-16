package com.example.rickandmortylist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.rickandmortylist.R
import com.example.rickandmortylist.network.Character

object UserComparator : DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem == newItem
    }
}

class RecyclerAdapter(
    diffCallback: DiffUtil.ItemCallback<Character> = UserComparator,
    val onClick: (Int) -> (Unit)
) :
    PagingDataAdapter<Character, RecyclerAdapter.CharacterViewHolder>(
        diffCallback
    ) {
    class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemIcon = itemView.findViewById<ImageView>(R.id.listImageView)
        var itemTitle = itemView.findViewById<TextView>(R.id.listTitle)
        var itemSubtitle = itemView.findViewById<TextView>(R.id.listSubtitle)

        fun bind(data: Character) {
            itemIcon.load(data.image) {
                crossfade(true)
                placeholder(R.drawable.ic_launcher_background)
                transformations(CircleCropTransformation())
            }
            itemTitle.text = data.name
            itemSubtitle.text = "${data.gender}, ${data.species}"
        }
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
            holder.itemView.setOnClickListener {
                onClick(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_character, parent, false)
        )
    }
}