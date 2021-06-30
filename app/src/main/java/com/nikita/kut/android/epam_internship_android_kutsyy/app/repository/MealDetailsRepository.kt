package com.nikita.kut.android.epam_internship_android_kutsyy.app.repository

import com.nikita.kut.android.epam_internship_android_kutsyy.app.data.network.RetrofitClient
import com.nikita.kut.android.epam_internship_android_kutsyy.feature.mealdetails.model.RemoteMealDetails
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException

class MealDetailsRepository {

    fun initMealDetailsFromNetwork(
        mealId: Int,
        onComplete: (RemoteMealDetails) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        RetrofitClient.retrofitService.getMealDetails(mealId).enqueue(
            object : Callback<RemoteMealDetails> {

                override fun onResponse(
                    call: Call<RemoteMealDetails>,
                    response: Response<RemoteMealDetails>
                ) {
                    if (response.isSuccessful) {
                        onComplete(response.body() ?: RemoteMealDetails(listOf()))
                    } else {
                        onError(RuntimeException("Incorrect status code"))
                    }
                }

                override fun onFailure(call: Call<RemoteMealDetails>, t: Throwable) {
                    onError(t)
                }
            }
        )
    }
}