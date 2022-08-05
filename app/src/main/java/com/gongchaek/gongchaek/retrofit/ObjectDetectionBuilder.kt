package com.gongchaek.gongchaek.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ObjectDetectionBuilder {
    val BASE_URL = "https://naveropenapi.apigw.ntruss.com/"

    val api : ObjectDetectionAPI by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ObjectDetectionAPI::class.java)
    }
}