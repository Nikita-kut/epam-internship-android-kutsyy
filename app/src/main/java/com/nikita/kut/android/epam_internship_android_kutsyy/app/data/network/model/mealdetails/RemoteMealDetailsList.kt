package com.nikita.kut.android.epam_internship_android_kutsyy.app.data.network.model.mealdetails

import com.google.gson.annotations.SerializedName

data class RemoteMealDetailsList(
    @SerializedName(value = "meals")
    val mealDetails: List<RemoteMealDetails>
) {
}