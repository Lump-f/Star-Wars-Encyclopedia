package com.example.starwarsencyclopedia.Network

import com.example.starwarsencyclopedia.Network.Model.Response
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SWApi {

    @GET("{query}")
    fun universalGet(
        @Path("query") query: String?,
        @Query("page") page: Int
    ) : Call<Response>

    companion object Factory {
        fun create(): SWApi {
            return Retrofit.Builder()
                .baseUrl("https://swapi.dev/api/")
                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
                .create(SWApi::class.java)
        }
    }
}