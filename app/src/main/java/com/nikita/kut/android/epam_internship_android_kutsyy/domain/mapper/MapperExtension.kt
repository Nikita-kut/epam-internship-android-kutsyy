package com.nikita.kut.android.epam_internship_android_kutsyy.domain.mapper

import com.nikita.kut.android.epam_internship_android_kutsyy.data.model.db.CategoryDB
import com.nikita.kut.android.epam_internship_android_kutsyy.data.model.db.MealDetailsDB
import com.nikita.kut.android.epam_internship_android_kutsyy.data.model.network.meal.MealListRemote
import com.nikita.kut.android.epam_internship_android_kutsyy.domain.model.CategoryEntity
import com.nikita.kut.android.epam_internship_android_kutsyy.domain.model.MealDetailsEntity
import com.nikita.kut.android.epam_internship_android_kutsyy.domain.model.MealEntity

fun List<CategoryDB>.toListCategoryEntity(): List<CategoryEntity> {
    val categoryList = mutableListOf<CategoryEntity>()
    for (i in this) {
        categoryList.add(CategoryEntity(i.id, i.categoryName, i.categoryPicture))
    }
    return categoryList.toList()
}

fun MealListRemote.toListMealEntity(): List<MealEntity> {
    val mealList = mutableListOf<MealEntity>()
    for (i in this.meals) {
        mealList.add(MealEntity(i.id, i.mealPicture, i.name))
    }
    return mealList
}

fun MealDetailsDB.toMealDetailsEntity(): MealDetailsEntity =
    MealDetailsEntity(id, mealName, mealCategory, mealArea, mealPicture, mealTags, mealIngredients)