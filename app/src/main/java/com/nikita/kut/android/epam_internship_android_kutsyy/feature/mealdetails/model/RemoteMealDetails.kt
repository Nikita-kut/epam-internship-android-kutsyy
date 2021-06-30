package com.nikita.kut.android.epam_internship_android_kutsyy.feature.mealdetails.model

import com.google.gson.annotations.SerializedName

data class RemoteMealDetails(
    @SerializedName(value = "meals")
    val mealDetails: List<MealDetails>
) {
}