package com.nikita.kut.android.epam_internship_android_kutsyy.feature.meallist.model

import com.google.gson.annotations.SerializedName

data class CategoryList(
    @SerializedName(value = "categories")
    val categories: List<Category>
)
