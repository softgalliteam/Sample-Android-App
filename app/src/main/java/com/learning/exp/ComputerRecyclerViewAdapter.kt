package com.learning.exp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ComputerRecyclerViewAdapter(
    private val list: ArrayList<ResponseDataItem>,
    private val onClickListener: (String) -> Unit
) :
    RecyclerView.Adapter<ComputerRecyclerViewAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.computer_list_row_item, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val computer = list[position]

        holder.compNameTextView?.text = computer.name
        holder.compDesTextView?.text = "CPU: ${computer.data?.cpuModel}, RAM: ${computer.data?.hardDiskSize}, Price: ${computer.data?.price}, Year: ${computer.data?.year}"

        holder.itemView.setOnClickListener {
            onClickListener(computer.name.toString())
        }

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return list.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val compIamge = itemView.findViewById<ImageView>(R.id.compLogoIv)
        val compNameTextView = itemView.findViewById<TextView>(R.id.compTitleTv)
        val compDesTextView = itemView.findViewById<TextView>(R.id.compDescTv)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newList: List<ResponseDataItem>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
}