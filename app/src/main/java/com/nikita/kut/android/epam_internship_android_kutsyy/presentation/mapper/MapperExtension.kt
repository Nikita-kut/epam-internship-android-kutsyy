package com.nikita.kut.android.epam_internship_android_kutsyy.presentation.mapper

import com.nikita.kut.android.epam_internship_android_kutsyy.domain.model.CategoryEntity
import com.nikita.kut.android.epam_internship_android_kutsyy.domain.model.MealDetailsEntity
import com.nikita.kut.android.epam_internship_android_kutsyy.domain.model.MealEntity
import com.nikita.kut.android.epam_internship_android_kutsyy.presentation.model.CategoryUI
import com.nikita.kut.android.epam_internship_android_kutsyy.presentation.model.MealDetailsUI
import com.nikita.kut.android.epam_internship_android_kutsyy.presentation.model.MealUI

fun List<MealEntity>.toListMealUI(): List<MealUI> {
    val mealList = mutableListOf<MealUI>()
    for (i in this) {
        mealList.add(MealUI(i.id, i.mealPicture, i.name))
    }
    return mealList
}

fun List<CategoryEntity>.toListCategoryUI(): List<CategoryUI> {
    val categoryList = mutableListOf<CategoryUI>()
    for (i in this) {
        categoryList.add(CategoryUI(i.id, i.categoryName, i.categoryPicture))
    }
    return categoryList.toList()
}

fun MealDetailsEntity.toMealDetailsUI(): MealDetailsUI =
    MealDetailsUI(id, mealName, mealCategory, mealArea, mealPicture, mealTags, mealIngredients)