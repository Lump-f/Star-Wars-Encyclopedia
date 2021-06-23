package com.example.starwarsencyclopedia.RecyclerView

import com.example.starwarsencyclopedia.Network.Model.Response

class ListDataSource {

    var data: Response? = null

    fun sendData(newData:Response?) {
        data = newData
    }
}