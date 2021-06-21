package com.example.starwarsencyclopedia.Network

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.lang.Error

class ResponseWrapper<T> : Serializable {
    @SerializedName("response")
    val data: T? = null
    @SerializedName("error")
    val error: Error? = null
}