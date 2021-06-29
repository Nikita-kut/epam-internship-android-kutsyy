package com.nikita.kut.android.epam_internship_android_kutsyy.app.data.network

import com.nikita.kut.android.epam_internship_android_kutsyy.feature.meallist.model.CategoryList
import retrofit2.Call
import retrofit2.http.GET

interface MealApi {

    @GET(value = "categories.php")
    fun getCategories(): Call<CategoryList>
}