package com.nikita.kut.android.epam_internship_android_kutsyy.app.data.network

import com.nikita.kut.android.epam_internship_android_kutsyy.feature.mealdetails.model.RemoteMealDetails
import com.nikita.kut.android.epam_internship_android_kutsyy.feature.meallist.model.CategoryList
import com.nikita.kut.android.epam_internship_android_kutsyy.feature.meallist.model.MealList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET(value = "categories.php")
    fun getCategories(): Call<CategoryList>

    @GET(value = "filter.php")
    fun getMeals(@Query("c") categoryName: String): Call<MealList>

    @GET(value = "lookup.php")
    fun getMealDetails(@Query("i") mealId: Int): Call<RemoteMealDetails>
}