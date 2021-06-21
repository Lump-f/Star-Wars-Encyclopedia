package com.example.starwarsencyclopedia.Network.Model

import com.example.starwarsencyclopedia.Network.SWApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Deprecated("SWApi companion object instead")
object NetworkService {
    fun create(): SWApi {
        return Retrofit.Builder()
            .baseUrl("https://swapi.dev/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SWApi::class.java)
    }
}