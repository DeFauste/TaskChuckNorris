package com.example.killerjoke.network

import com.example.killerjoke.data.entities.Jokes
import com.example.killerjoke.other.Constants.API_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private val retrofit =
    Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(API_URL)
        .build()

interface ApiService {
    @GET("/jokes/random/{number}")
    suspend fun getJokes(
        @Path("number") valJoke: Int
    ): Jokes
}

object ServiceApi {
    val retrofitService: ApiService by lazy { retrofit.create(ApiService::class.java) }
}
