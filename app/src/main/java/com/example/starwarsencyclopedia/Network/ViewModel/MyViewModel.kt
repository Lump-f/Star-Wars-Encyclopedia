package com.example.starwarsencyclopedia.Network.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.starwarsencyclopedia.Network.Event
import com.example.starwarsencyclopedia.Network.Model.NetworkService
import com.example.starwarsencyclopedia.Network.ResponseWrapper
import com.example.starwarsencyclopedia.Network.SWApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class MyViewModel : ViewModel() {

    var api: SWApi = NetworkService.create()

    fun <T> sendRequest(
        liveData: MutableLiveData<Event<T>>,
//        request: () -> ResponseWrapper<T>
        request: suspend () -> ResponseWrapper<T>

    ) {

        liveData.postValue(Event.loading())

        this.viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = request.invoke()
                if (response.data != null) {
                    liveData.postValue(Event.success(response.data))
                } else if (response.error != null) {
                    liveData.postValue(Event.error(response.error))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                liveData.postValue(Event.error(null))
            }
        }
    }

}