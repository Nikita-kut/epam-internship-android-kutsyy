package com.nikita.kut.android.epam_internship_android_kutsyy.app.data.model.meal

import com.google.gson.annotations.SerializedName

data class RemoteMeal(
    @SerializedName("idMeal")
    val id: Int,
    @SerializedName(value = "strMealThumb")
    val mealPicture: String,
    @SerializedName(value = "strMeal")
    val name: String,
)