package com.olamachia.simpleblogapp.implementation2.model

import com.olamachia.simpleblogapp.implementation1.util.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

object MVCRetrofitInstance {
private val retrofit by lazy {

    Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())

        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

}

    val api: IMVCBlogAPI by lazy {
        retrofit.create(IMVCBlogAPI::class.java)
    }

}