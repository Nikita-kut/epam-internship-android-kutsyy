package com.nikita.kut.android.epam_internship_android_kutsyy.feature.mealdetails.model

data class MealDetailsUIModel(
    val id: Int,
    val mealName: String,
    val mealCategory: String,
    val mealArea: String,
    val mealPicture: String,
    val mealTag: String,
    val mealIngredients: String
)