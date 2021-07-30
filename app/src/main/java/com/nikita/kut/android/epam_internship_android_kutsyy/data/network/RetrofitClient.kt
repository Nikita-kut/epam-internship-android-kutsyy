package com.nikita.kut.android.epam_internship_android_kutsyy.data.network

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"

    private val retrofitClient: Retrofit by lazy {
        getRetrofitClient(BASE_URL)
    }

    val mealsApi: MealsApi
        get() = retrofitClient.create(MealsApi::class.java)

    private fun getRetrofitClient(baseUrl: String): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}