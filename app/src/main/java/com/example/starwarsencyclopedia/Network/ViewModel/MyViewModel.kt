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

    fun sRequest(
        liveData: MutableLiveData<Event<Response>>,
        request: () -> Call<Response>
//        request: suspend () -> ResponseWrapper<T>

    ) {

//        this.viewModelScope.launch() {
//            try {
//                val response = request.invoke()
//                if (response.data != null) {
//                    liveData.postValue(Event.success(response.data))
//                } else if (response.error != null) {
//                    liveData.postValue(Event.error(response.error))
//                }
//            } catch (e: Exception) {
//                e.printStackTrace()
//                liveData.postValue(Event.error(null))
//            }
//        }

        liveData.postValue(Event.loading())

            val response = request.invoke()
            response.enqueue(object : Callback<Response> {

                override fun onResponse(
                    call: Call<Response>,
                    response: retrofit2.Response<Response>
                ) {
                    if (response.isSuccessful) {
                        val sss = response.body()
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