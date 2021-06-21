package com.example.starwarsencyclopedia.Network.Model

import com.example.starwarsencyclopedia.Network.SWApi
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NetworkService {
    fun create(): SWApi {
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://swapi.dev/api/")
            .build()
            .create(SWApi::class.java)
    }
}