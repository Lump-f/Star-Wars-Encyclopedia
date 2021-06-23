package com.example.starwarsencyclopedia.RecyclerView

import com.example.starwarsencyclopedia.Network.Model.Response

class ListDataSource {

    var data: ArrayList<Response.Item?> = ArrayList()

    fun sendData(newData: ArrayList<Response.Item?>?) {
        data.addAll(newData!!)
    }
}
