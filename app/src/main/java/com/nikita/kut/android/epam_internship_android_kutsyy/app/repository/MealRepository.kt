package com.nikita.kut.android.epam_internship_android_kutsyy.app.repository

import com.nikita.kut.android.epam_internship_android_kutsyy.app.data.network.RetrofitClient
import com.nikita.kut.android.epam_internship_android_kutsyy.app.data.model.category.RemoteCategoryList
import com.nikita.kut.android.epam_internship_android_kutsyy.app.data.model.meal.RemoteMealList
import com.nikita.kut.android.epam_internship_android_kutsyy.app.data.model.mealdetails.RemoteMealDetailsList
import com.nikita.kut.android.epam_internship_android_kutsyy.app.util.exception.IncorrectStatusCodeException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException

class MealRepository {

    fun fetchMealList(
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
                        onError(IncorrectStatusCodeException("Incorrect status code"))
                    }
                }

                override fun onFailure(call: Call<RemoteMealList>, t: Throwable) {
                    onError(t)
                }
            }
        )
    }

    fun fetchMealDetails(
        mealId: Int,
        onComplete: (RemoteMealDetailsList) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        RetrofitClient.retrofitService.getMealDetails(mealId).enqueue(
            object : Callback<RemoteMealDetailsList> {

                override fun onResponse(
                    call: Call<RemoteMealDetailsList>,
                    response: Response<RemoteMealDetailsList>
                ) {
                    if (response.isSuccessful) {
                        onComplete(response.body() ?: RemoteMealDetailsList(listOf()))
                    } else {
                        onError(IncorrectStatusCodeException("Incorrect status code"))
                    }
                }

                override fun onFailure(call: Call<RemoteMealDetailsList>, t: Throwable) {
                    onError(t)
                }
            }
        )
    }
}