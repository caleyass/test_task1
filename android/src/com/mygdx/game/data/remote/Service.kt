package com.mygdx.game.data.remote

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://yearnewsantasa.live"

interface Service {
    @GET("test.txt")
    fun getResponse() : Call<String>
}

object Api {
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
    val retrofitService: Service by lazy { retrofit.create(Service::class.java) }
}