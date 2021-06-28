package com.nikita.kut.android.epam_internship_android_kutsyy.feature.meallist.model

import androidx.annotation.DrawableRes
import com.google.gson.annotations.SerializedName
import kotlin.random.Random

data class Category(
    @SerializedName(value = "idCategory")
    val id: Int,
    @SerializedName(value = "")
    val categoryPicture: String,
    var clicked: Boolean = false
)