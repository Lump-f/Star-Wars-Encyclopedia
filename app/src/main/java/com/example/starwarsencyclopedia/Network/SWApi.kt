package com.example.starwarsencyclopedia.Network

import com.example.starwarsencyclopedia.Network.Model.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SWApi {

    @GET("people")
    fun request(
//    suspend fun request(
//        @Query(".") requestParameter: String,
        @Query("page") page: Int
    ): ResponseWrapper<Response>

//    companion object Factory {
//        fun create(): SWApi {
//            return Retrofit.Builder()
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .baseUrl("https://swapi.dev/api/")
//                .build()
//                .create(SWApi::class.java)
//        }
//    }
}

//interface SimpleService {
//    @GET("/simple/{id}")
//    fun getSimple(@Path("id") id: String?): Call<Response>?
//}