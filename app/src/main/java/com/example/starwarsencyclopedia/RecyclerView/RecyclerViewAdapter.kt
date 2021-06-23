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

//    val itemClicked = MutableLiveData<Response>()

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var myItemView = view
        private var item: Response.Item? = null

        fun updateWith(item: Response.Item?) {
            this.item = item
            if (item?.name != null) { // Костыль
                myItemView.itemName.text = item.name
            } else {
                myItemView.itemName.text = item?.title
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = ItemView(parent.context)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.updateWith(dataSource.data[position])
    }

    override fun getItemCount(): Int {
        return dataSource.data.size
    }

    fun sendData(newData: ArrayList<Response.Item?>?) {
        dataSource.sendData(newData)
        notifyDataSetChanged()
    }

}
