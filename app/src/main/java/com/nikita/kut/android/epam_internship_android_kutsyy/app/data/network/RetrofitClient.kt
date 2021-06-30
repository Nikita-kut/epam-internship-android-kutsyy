package com.nikita.kut.android.epam_internship_android_kutsyy.app.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"

    private var retrofitClient: Retrofit? = null

    private fun getRetrofitClient(baseUrl: String): Retrofit {
        if (retrofitClient == null) {
            retrofitClient = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofitClient ?: error("Retrofit init eror")
    }

    val retrofitService: RetrofitService
        get() = getRetrofitClient(BASE_URL).create(RetrofitService::class.java)

}