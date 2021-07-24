package com.nikita.kut.android.epam_internship_android_kutsyy.presentation.model

data class CategoryUI(
    val id: Int,
    val categoryName: String,
    val categoryPicture: String,
    var isSelected: Boolean = false
)