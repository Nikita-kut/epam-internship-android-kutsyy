package com.nikita.kut.android.epam_internship_android_kutsyy.util

import com.google.common.base.Joiner
import com.nikita.kut.android.epam_internship_android_kutsyy.data.model.db.CategoryEntity
import com.nikita.kut.android.epam_internship_android_kutsyy.data.model.db.MealDetailsEntity
import com.nikita.kut.android.epam_internship_android_kutsyy.data.model.db.MealEntity
import com.nikita.kut.android.epam_internship_android_kutsyy.data.model.network.category.CategoryListRemote
import com.nikita.kut.android.epam_internship_android_kutsyy.data.model.network.meal.MealListRemote
import com.nikita.kut.android.epam_internship_android_kutsyy.data.model.network.mealdetails.MealDetailsRemote
import com.nikita.kut.android.epam_internship_android_kutsyy.presentation.model.MealDetailsUI
import com.nikita.kut.android.epam_internship_android_kutsyy.presentation.model.CategoryUI
import com.nikita.kut.android.epam_internship_android_kutsyy.presentation.model.MealUI

fun MealListRemote.toListMealUIModel(): List<MealUI> {
    val mealList = mutableListOf<MealUI>()
    for (i in this.meals) {
        mealList.add(MealUI(i.id, i.mealPicture, i.name))
    }
    return mealList
}

fun MealListRemote.toListDbMealModel(): List<MealEntity> {
    val mealList = mutableListOf<MealEntity>()
    for (i in this.meals) {
        mealList.add(MealEntity(i.id, i.mealPicture, i.name))
    }
    return mealList
}

fun List<MealEntity>.toListMealUIModel(): List<MealUI> {
    val mealList = mutableListOf<MealUI>()
    for (i in this) {
        mealList.add(MealUI(i.id, i.mealPicture, i.name))
    }
    return mealList
}

fun CategoryListRemote.toListCategoryUIModel(): List<CategoryUI> {
    val categoryList = mutableListOf<CategoryUI>()
    for (i in this.categories) {
        categoryList.add(CategoryUI(i.id, i.categoryName, i.categoryPicture))
    }
    return categoryList.toList()
}

fun CategoryListRemote.toListDbCategoryModel(): List<CategoryEntity> {
    val categoryList = mutableListOf<CategoryEntity>()
    for (i in this.categories) {
        categoryList.add(CategoryEntity(i.id, i.categoryName, i.categoryPicture))
    }
    return categoryList.toList()
}

fun CategoryEntity.toCategoryUIModel(): CategoryUI =
    CategoryUI(this.id, this.categoryName, this.categoryPicture)

fun List<CategoryEntity>.toListCategoryUIModel(): List<CategoryUI> {
    val categoryList = mutableListOf<CategoryUI>()
    for (i in this) {
        categoryList.add(CategoryUI(i.id, i.categoryName, i.categoryPicture))
    }
    return categoryList.toList()
}


fun MealDetailsRemote.toMealDetailsUIModel(): MealDetailsUI {
    val ingredientsMap = mapOf(
        ingredient1 to measure1,
        ingredient2 to measure2,
        ingredient3 to measure3,
        ingredient4 to measure4,
        ingredient5 to measure5,
        ingredient6 to measure6,
        ingredient7 to measure7,
        ingredient8 to measure8,
        ingredient9 to measure9,
        ingredient10 to measure10,
        ingredient11 to measure11,
        ingredient12 to measure12,
        ingredient13 to measure13,
        ingredient14 to measure14,
        ingredient15 to measure15,
        ingredient16 to measure16,
        ingredient17 to measure17,
        ingredient18 to measure18,
        ingredient19 to measure19,
        ingredient20 to measure20,
    )
    val filteredIngredientMap = ingredientsMap.filterKeys { !it.isNullOrEmpty() }
    val ingredients: String =
        Joiner.on(",\n").withKeyValueSeparator(" ").join(filteredIngredientMap)
    return MealDetailsUI(
        id = id,
        mealName = mealName,
        mealCategory = mealCategory,
        mealArea = mealArea,
        mealPicture = mealPicture,
        mealTags = mealTag?.split(",") ?: listOf(),
        mealIngredients = ingredients
    )
}

fun MealDetailsRemote.toDbMealDetailsModel(): MealDetailsEntity {
    val ingredientsMap = mapOf(
        ingredient1 to measure1,
        ingredient2 to measure2,
        ingredient3 to measure3,
        ingredient4 to measure4,
        ingredient5 to measure5,
        ingredient6 to measure6,
        ingredient7 to measure7,
        ingredient8 to measure8,
        ingredient9 to measure9,
        ingredient10 to measure10,
        ingredient11 to measure11,
        ingredient12 to measure12,
        ingredient13 to measure13,
        ingredient14 to measure14,
        ingredient15 to measure15,
        ingredient16 to measure16,
        ingredient17 to measure17,
        ingredient18 to measure18,
        ingredient19 to measure19,
        ingredient20 to measure20,
    )
    val filteredIngredientMap = ingredientsMap.filterKeys { !it.isNullOrEmpty() }
    val ingredients: String =
        Joiner.on(",\n").withKeyValueSeparator(" ").join(filteredIngredientMap)
    return MealDetailsEntity(
        id = id,
        mealName = mealName,
        mealCategory = mealCategory,
        mealArea = mealArea,
        mealPicture = mealPicture,
        mealTags = mealTag?.split(",") ?: listOf(),
        mealIngredients = ingredients
    )
}

fun MealDetailsEntity.toMealDetailsUIModel(): MealDetailsUI =
    MealDetailsUI(id, mealName, mealCategory, mealArea, mealPicture, mealTags, mealIngredients)

