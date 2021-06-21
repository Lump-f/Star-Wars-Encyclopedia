package com.example.starwarsencyclopedia.Network

import com.example.starwarsencyclopedia.Network.Model.Response
import okhttp3.ResponseBody
import java.lang.Error

class Event<T>(val status: Status, val data: T?, val error: ResponseBody?) {

    companion object {
        fun <T> loading(): Event<T> {
            return Event(Status.LOADING, null, null)
        }

        fun success(data: Response?): Event<Response> {
            return Event(Status.SUCCESS, data, null)
        }

        fun <T> error(error: ResponseBody?): Event<T> {
            return Event(Status.ERROR, null, error)
        }
    }

}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}
