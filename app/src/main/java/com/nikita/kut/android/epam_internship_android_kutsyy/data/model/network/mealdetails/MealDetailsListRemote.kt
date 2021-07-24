package com.nikita.kut.android.epam_internship_android_kutsyy.data.model.network.mealdetails

import com.google.gson.annotations.SerializedName

data class MealDetailsListRemote(
    @SerializedName(value = "meals")
    val mealDetails: List<MealDetailsRemote>
) {
}