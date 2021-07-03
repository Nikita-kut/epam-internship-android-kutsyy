package com.nikita.kut.android.epam_internship_android_kutsyy.app.repository

import com.nikita.kut.android.epam_internship_android_kutsyy.app.data.network.RetrofitClient
import com.nikita.kut.android.epam_internship_android_kutsyy.app.data.model.category.RemoteCategoryList
import com.nikita.kut.android.epam_internship_android_kutsyy.app.data.model.meal.RemoteMealList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException

class MealListRepository {

    fun fetchMeals(
        categoryName: String,
        onComplete: (RemoteMealList) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        RetrofitClient.retrofitService.getMeals(categoryName).enqueue(
            object : Callback<RemoteMealList> {

                override fun onResponse(
                    call: Call<RemoteMealList>,
                    response: Response<RemoteMealList>
                ) {
                    if (response.isSuccessful) {
                        onComplete(response.body() ?: RemoteMealList(listOf()))
                    } else {
                        onError(RuntimeException("Incorrect status code"))
                    }
                }

                override fun onFailure(call: Call<RemoteMealList>, t: Throwable) {
                    onError(t)
                }
            }
        )
    }

    fun fetchCategories(
        onComplete: (RemoteCategoryList) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        RetrofitClient.retrofitService.getCategories().enqueue(
            object : Callback<RemoteCategoryList> {

                override fun onResponse(
                    call: Call<RemoteCategoryList>,
                    response: Response<RemoteCategoryList>
                ) {
                    if (response.isSuccessful) {
                        onComplete(response.body() ?: RemoteCategoryList((listOf())))
                    } else {
                        onError(RuntimeException("Incorrect status code"))
                    }
                }

                override fun onFailure(call: Call<RemoteCategoryList>, t: Throwable) {
                    onError(t)
                }
            }
        )
    }
}