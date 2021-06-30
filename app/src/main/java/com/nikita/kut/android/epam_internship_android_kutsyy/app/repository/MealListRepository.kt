package com.nikita.kut.android.epam_internship_android_kutsyy.app.repository

import com.nikita.kut.android.epam_internship_android_kutsyy.app.data.network.RetrofitClient
import com.nikita.kut.android.epam_internship_android_kutsyy.feature.meallist.model.CategoryList
import com.nikita.kut.android.epam_internship_android_kutsyy.feature.meallist.model.MealList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException

class MealListRepository {

    fun initMealListFromNetwork(
        categoryName: String,
        onComplete: (MealList) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        RetrofitClient.retrofitService.getMeals(categoryName).enqueue(
            object : Callback<MealList> {

                override fun onResponse(
                    call: Call<MealList>,
                    response: Response<MealList>
                ) {
                    if (response.isSuccessful) {
                        onComplete(response.body() ?: MealList(listOf()))
                    } else {
                        onError(RuntimeException("Incorrect status code"))
                    }
                }

                override fun onFailure(call: Call<MealList>, t: Throwable) {
                    onError(t)
                }
            }
        )
    }

    fun initMealCategoriesFromNetwork(
        onComplete: (CategoryList) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        RetrofitClient.retrofitService.getCategories().enqueue(
            object : Callback<CategoryList> {

                override fun onResponse(
                    call: Call<CategoryList>,
                    response: Response<CategoryList>
                ) {
                    if (response.isSuccessful) {
                        onComplete(response.body() ?: CategoryList((listOf())))
                    } else {
                        onError(RuntimeException("Incorrect status code"))
                    }
                }

                override fun onFailure(call: Call<CategoryList>, t: Throwable) {
                    onError(t)
                }
            }
        )
    }
}