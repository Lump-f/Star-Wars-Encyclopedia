package com.example.starwarsencyclopedia.Network.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.starwarsencyclopedia.Network.Event
import com.example.starwarsencyclopedia.Network.Model.Response
import com.example.starwarsencyclopedia.Network.SWApi
import retrofit2.Call
import retrofit2.Callback

abstract class MyViewModel : ViewModel() {

    var api: SWApi = SWApi.create()

    fun sendRequest(
        liveData: MutableLiveData<Event<Response>>,
        request: () -> Call<Response>
    ) {

        liveData.postValue(Event.loading())

            val response = request.invoke()
            response.enqueue(object : Callback<Response> {

                override fun onResponse(
                    call: Call<Response>,
                    response: retrofit2.Response<Response>
                ) {
                    if (response.isSuccessful) {
                        liveData.postValue(Event.success(response.body()))
                    } else {
                        liveData.postValue(Event.error(response.errorBody()))
                    }
                }

                override fun onFailure(call: Call<Response>, t: Throwable) {
                    t.printStackTrace()
                    liveData.postValue(Event.error(null))
                }
            })
    }

}