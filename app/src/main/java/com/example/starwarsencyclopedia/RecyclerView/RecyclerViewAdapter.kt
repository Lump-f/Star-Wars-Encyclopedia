package com.example.starwarsencyclopedia.RecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsencyclopedia.Network.Model.Response
import com.example.starwarsencyclopedia.R
import kotlinx.android.synthetic.main.item_view.view.*

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {
    var dataSource = listOf<Response.Item?>()
//    val itemClicked = MutableLiveData<Response>()

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvItemName: TextView? = null

        init {
            tvItemName = itemView.itemName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_view, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvItemName?.text = dataSource[position]?.title ?: "Null"
    }

    override fun getItemCount(): Int {
        return dataSource.size
    }
}
