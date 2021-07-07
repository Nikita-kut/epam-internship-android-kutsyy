package com.nikita.kut.android.epam_internship_android_kutsyy.app.data.network

import com.nikita.kut.android.epam_internship_android_kutsyy.app.data.model.mealdetails.RemoteMealDetailsList
import com.nikita.kut.android.epam_internship_android_kutsyy.app.data.model.category.RemoteCategoryList
import com.nikita.kut.android.epam_internship_android_kutsyy.app.data.model.meal.RemoteMealList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET(value = "categories.php")
    fun getCategories(): Call<RemoteCategoryList>

    @GET(value = "filter.php")
    fun getMeals(@Query("c") categoryName: String): Call<RemoteMealList>

    @GET(value = "lookup.php")
    fun getMealDetails(@Query("i") mealId: Int): Call<RemoteMealDetailsList>
}