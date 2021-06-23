package com.example.starwarsencyclopedia.RecyclerView

import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsencyclopedia.Network.Model.Response
import com.example.starwarsencyclopedia.RecyclerView.Model.ItemView
import kotlinx.android.synthetic.main.item_view.view.*

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    var dataSource = ListDataSource()

    val itemClicked = MutableLiveData<Response>()

    class MyViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun updateWith(item: String?) {
            view.itemName.text = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = ItemView(parent.context)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if (dataSource.data?.results?.get(position)?.name != null) {
            holder.updateWith(dataSource.data?.results?.get(position)?.name)
        } else {
            holder.updateWith(dataSource.data?.results?.get(position)?.title)
        }
    }

    override fun getItemCount(): Int {
        return 0
    }

    fun sendData(newData: Response?) {
        dataSource.sendData(newData)
        notifyDataSetChanged()
    }

}