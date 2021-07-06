package com.nikita.kut.android.epam_internship_android_kutsyy.app.repository

import com.nikita.kut.android.epam_internship_android_kutsyy.app.data.model.category.RemoteCategoryList
import com.nikita.kut.android.epam_internship_android_kutsyy.app.data.network.RetrofitClient
import com.nikita.kut.android.epam_internship_android_kutsyy.app.data.model.mealdetails.RemoteMealDetailsList
import com.nikita.kut.android.epam_internship_android_kutsyy.app.util.exception.IncorrectStatusCodeException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException

class CategoryRepository {

    fun fetchCategoryList(
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
                        onError(IncorrectStatusCodeException("Incorrect status code"))
                    }
                }

                override fun onFailure(call: Call<RemoteCategoryList>, t: Throwable) {
                    onError(t)
                }
            }
        )
    }
}