package com.zestworks.trendingrepositories.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zestworks.data.model.TrendingDevelopers
import com.zestworks.data.model.TrendingRepositories
import com.zestworks.trendingrepositories.R
import de.hdodenhof.circleimageview.CircleImageView

class ListingAdapter(var listItems: List<Any>, val onClick: OnCLick? = null): RecyclerView.Adapter<ListingAdapter.Listholder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Listholder {
        val view =
            LayoutInflater.from(parent.context!!)
                .inflate(R.layout.list_holder, parent, false)
        return Listholder(view)
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    fun updateListItems(listItems: List<Any>) {
        this.listItems = listItems
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: Listholder, position: Int) {
        val item = listItems[position]
        if(item is TrendingRepositories) {
            holder.title.text = item.name
            holder.description.text = item.description
            Glide.with(holder.imageView)
                .load(item.avatar)
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.imageView)
            if(onClick != null) {
                holder.itemView.setOnClickListener {
                    onClick.onClick(item.url)
                }
            }
        } else if(item is TrendingDevelopers) {
            holder.title.text = item.name
            holder.description.text = item.username
            Glide.with(holder.imageView)
                .load(item.avatar)
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.imageView)
            if(onClick != null) {
                holder.itemView.setOnClickListener {
                    onClick.onClick(item.url)
                }
            }
        }
    }

    inner class Listholder(itemView: View): RecyclerView.ViewHolder(itemView){
        val title = itemView.findViewById<TextView>(R.id.title)
        val description = itemView.findViewById<TextView>(R.id.description)
        val imageView = itemView.findViewById<CircleImageView>(R.id.author_image)
    }

    interface OnCLick {
        fun onClick(url: String)
    }
}