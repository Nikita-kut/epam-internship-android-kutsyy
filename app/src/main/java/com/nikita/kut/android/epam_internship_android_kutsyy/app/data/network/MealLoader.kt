package com.nikita.kut.android.epam_internship_android_kutsyy.app.data.network

import retrofit2.Retrofit

class MealLoader {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://www.themealdb.com/api/json/v1/1/")

}