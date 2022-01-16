package com.olamachia.simpleblogapp.implementation1.api

import com.olamachia.simpleblogapp.implementation1.util.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
private val retrofit by lazy {

    Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

}

    val api by lazy {
        retrofit.create(IBlogAPI::class.java)
    }

}