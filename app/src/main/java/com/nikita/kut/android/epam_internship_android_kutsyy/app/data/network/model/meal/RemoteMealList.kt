package com.nikita.kut.android.epam_internship_android_kutsyy.app.data.network.model.meal

import com.google.gson.annotations.SerializedName

data class RemoteMealList(
    @SerializedName( value ="meals")
    val meals: List<RemoteMeal>
)
