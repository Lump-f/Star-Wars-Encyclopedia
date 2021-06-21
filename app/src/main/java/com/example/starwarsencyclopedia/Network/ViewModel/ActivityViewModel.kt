package com.example.starwarsencyclopedia.Network.ViewModel

import androidx.lifecycle.MutableLiveData
import com.example.starwarsencyclopedia.Network.Event
import com.example.starwarsencyclopedia.Network.Model.Response

class ActivityViewModel: MyViewModel() {

    val items = MutableLiveData<Event<Response>>()

    fun sendRequest(query: String, page: Int) {
        sendRequest(items) {
            api.request(
//                requestParameter = query,
                page = page
            )
        }
    }
}