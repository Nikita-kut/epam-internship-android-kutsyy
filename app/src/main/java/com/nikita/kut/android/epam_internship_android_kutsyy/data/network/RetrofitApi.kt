package com.nikita.kut.android.epam_internship_android_kutsyy.data.network

import com.nikita.kut.android.epam_internship_android_kutsyy.data.model.network.mealdetails.MealDetailsListRemote
import com.nikita.kut.android.epam_internship_android_kutsyy.data.model.network.category.CategoryListRemote
import com.nikita.kut.android.epam_internship_android_kutsyy.data.model.network.meal.MealListRemote
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApi {
    @GET(value = "categories.php")
    fun getCategories(): Single<CategoryListRemote>

    @GET(value = "filter.php")
    fun getMeals(@Query("c") categoryName: String): Single<MealListRemote>

    @GET(value = "lookup.php")
    fun getMealDetails(@Query("i") mealId: Int): Single<MealDetailsListRemote>
}