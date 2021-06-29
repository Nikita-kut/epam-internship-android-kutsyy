package com.nikita.kut.android.epam_internship_android_kutsyy.app.data.network

import com.nikita.kut.android.epam_internship_android_kutsyy.feature.meallist.model.CategoryList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MealLoader {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://www.themealdb.com/api/json/v1/1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val mealApi = retrofit.create(MealApi::class.java)

    var categories = CategoryList(listOf())

    fun initCategories() {
        mealApi.getCategories().enqueue(
            object : Callback<CategoryList> {
                override fun onResponse(
                    call: Call<CategoryList>,
                    response: Response<CategoryList>
                ) {
                    TODO("Not yet implemented")
                }

                override fun onFailure(call: Call<CategoryList>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }


}