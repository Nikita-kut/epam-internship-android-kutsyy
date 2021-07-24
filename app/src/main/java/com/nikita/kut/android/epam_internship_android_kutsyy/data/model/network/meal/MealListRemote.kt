package com.nikita.kut.android.epam_internship_android_kutsyy.data.model.network.meal

import com.google.gson.annotations.SerializedName

data class MealListRemote(
    @SerializedName( value ="meals")
    val meals: List<MealRemote>
)
