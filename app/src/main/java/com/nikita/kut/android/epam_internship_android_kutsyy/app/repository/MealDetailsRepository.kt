package com.nikita.kut.android.epam_internship_android_kutsyy.app.repository

import com.nikita.kut.android.epam_internship_android_kutsyy.app.data.network.RetrofitClient
import com.nikita.kut.android.epam_internship_android_kutsyy.app.data.model.mealdetails.RemoteMealDetailsList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException

class MealDetailsRepository {

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
                        onError(RuntimeException("Incorrect status code"))
                    }
                }

                override fun onFailure(call: Call<RemoteMealDetailsList>, t: Throwable) {
                    onError(t)
                }
            }
        )
    }
}