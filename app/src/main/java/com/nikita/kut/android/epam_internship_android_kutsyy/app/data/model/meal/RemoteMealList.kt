package com.nikita.kut.android.epam_internship_android_kutsyy.app.data.model.meal

import com.google.gson.annotations.SerializedName
import com.nikita.kut.android.epam_internship_android_kutsyy.app.data.model.meal.RemoteMeal

data class RemoteMealList(
    @SerializedName( value ="meals")
    val meals: List<RemoteMeal>
)
