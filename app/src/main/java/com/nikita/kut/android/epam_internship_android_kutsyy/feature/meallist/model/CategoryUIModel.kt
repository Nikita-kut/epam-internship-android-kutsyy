package com.nikita.kut.android.epam_internship_android_kutsyy.feature.meallist.model

data class CategoryUIModel(
    val id: Int,
    val categoryName: String,
    val categoryPicture: String,
    var isSelected: Boolean = false
)