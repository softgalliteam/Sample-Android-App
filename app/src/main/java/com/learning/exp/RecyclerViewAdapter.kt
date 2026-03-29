package com.learning.exp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(private val list: List<FruitListItem>, private val onClickListener: (String) -> Unit) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_row_item, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val fruit = list[position]

        holder.nameTextView?.text = fruit.fruitName
        holder.phoneTextView?.text = fruit.fruitDescription
        holder.image?.setImageResource(fruit.fruitImage)
        holder.itemView.setOnClickListener {
            onClickListener(fruit.fruitName)
        }

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return list.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image = itemView.findViewById<ImageView>(R.id.logoIv)
        val nameTextView = itemView.findViewById<TextView>(R.id.titleTv)
        val phoneTextView = itemView.findViewById<TextView>(R.id.descTv)
    }
}