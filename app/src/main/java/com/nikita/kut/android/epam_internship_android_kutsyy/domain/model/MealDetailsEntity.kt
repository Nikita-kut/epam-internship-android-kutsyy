package com.nikita.kut.android.epam_internship_android_kutsyy.domain.model

data class MealDetailsEntity(
    val id: Int,
    val mealName: String,
    val mealCategory: String,
    val mealArea: String,
    val mealPicture: String,
    val mealTags: List<String>,
    val mealIngredients: String
)
