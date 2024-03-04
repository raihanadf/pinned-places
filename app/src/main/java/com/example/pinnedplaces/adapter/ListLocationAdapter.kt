package com.example.pinnedplaces.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.pinnedplaces.DetailActivity
import com.example.pinnedplaces.R
import com.example.pinnedplaces.model.Location

class ListLocationAdapter(private val listLocation: ArrayList<Location>): RecyclerView.Adapter<ListLocationAdapter.ListLocationViewHolder>() {
    class ListLocationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_photo)
        val title: TextView = itemView.findViewById(R.id.text_title)
        val second: TextView = itemView.findViewById(R.id.text_second)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListLocationViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_location, parent, false)
        return ListLocationViewHolder(view)
    }

    override fun getItemCount(): Int = listLocation.size

    override fun onBindViewHolder(holder: ListLocationViewHolder, position: Int) {
        holder.imgPhoto.load(listLocation[position].imgPhoto){
            placeholder(R.drawable.loading_img)
            error(R.drawable.disconnected)
        }
        holder.title.text = listLocation[position].title
        holder.second.text = listLocation[position].second
        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.location_detail, listLocation[position])
            it.context.startActivity(intent)
        }
    }
}