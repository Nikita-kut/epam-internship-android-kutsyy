package com.nikita.kut.android.epam_internship_android_kutsyy.app.data.model.category

import androidx.annotation.DrawableRes
import com.google.gson.annotations.SerializedName
import kotlin.random.Random

data class RemoteCategory(
    @SerializedName(value = "idCategory")
    val id: Int,
    @SerializedName(value = "strCategory")
    val categoryName: String,
    @SerializedName(value = "strCategoryThumb")
    val categoryPicture: String,
)