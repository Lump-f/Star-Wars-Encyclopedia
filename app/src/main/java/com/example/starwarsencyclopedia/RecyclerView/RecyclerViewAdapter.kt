package com.example.starwarsencyclopedia.RecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsencyclopedia.Network.Model.Response
import com.example.starwarsencyclopedia.R
import kotlinx.android.synthetic.main.item_view.view.*

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    var results = listOf<Response.Item?>()
    var copyOfResults = listOf<Response.Item?>()

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var rvItemName: TextView? = null
        var rvFavoriteBtn: Button? = null

        init {
            rvItemName = itemView.itemName
            rvFavoriteBtn = itemView.favoriteBtn
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_view, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.rvItemName?.text = results[position]?.title ?: results[position]?.name
    }

    override fun getItemCount(): Int {
        return results.size
    }

    fun updateList(newText: String?) {
        copyOfResults.let {
            results = it.filter { item ->
                (item?.name ?: item?.title)!!.startsWith(newText.toString(), true) ||
                        (item?.name ?: item?.title)!!.contains(newText.toString(), true)
            }
        }
        notifyDataSetChanged()
    }
}