package com.learning.exp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class FruitListAdaptor(context: Context, private val fruitList: List<FruitListItem>) :
    ArrayAdapter<FruitListItem>(context, 0, fruitList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView = convertView
        val fruit = getItem(position)

        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.list_row_item, parent, false)
        }

        // Using the ViewHolder pattern for performance is highly recommended
        val image = itemView?.findViewById<ImageView>(R.id.logoIv)
        val nameTextView = itemView?.findViewById<TextView>(R.id.titleTv)
        val phoneTextView = itemView?.findViewById<TextView>(R.id.descTv)

        nameTextView?.text = fruit?.fruitName
        phoneTextView?.text = fruit?.fruitDescription
        image?.setImageResource(fruit?.fruitImage ?: 0)

        return itemView!!
    }
}