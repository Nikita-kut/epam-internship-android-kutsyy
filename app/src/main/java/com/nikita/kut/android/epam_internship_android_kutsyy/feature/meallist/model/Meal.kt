package com.nikita.kut.android.epam_internship_android_kutsyy.feature.meallist.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlin.random.Random

@Parcelize
data class Meal(
    @SerializedName("idMeal")
    val id: Int,
    @SerializedName(value = "strMealThumb")
    val mealPicture: String,
    @SerializedName(value = "strMeal")
    val name: String,
): Parcelable