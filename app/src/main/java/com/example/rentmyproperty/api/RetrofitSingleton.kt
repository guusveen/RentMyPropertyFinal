package com.example.rentmyproperty.api

import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitSingleton {
    var BASE_URL = "http://10.0.2.2:8080/"
    private val moshi = Moshi.Builder()
        .add(com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory())
        .add(LocalDateAdapter())
        .build()
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
}