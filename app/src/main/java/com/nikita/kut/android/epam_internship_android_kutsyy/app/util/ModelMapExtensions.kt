package com.nikita.kut.android.epam_internship_android_kutsyy.app.util

import com.google.common.base.Joiner
import com.nikita.kut.android.epam_internship_android_kutsyy.app.data.model.category.RemoteCategoryList
import com.nikita.kut.android.epam_internship_android_kutsyy.app.data.model.meal.RemoteMealList
import com.nikita.kut.android.epam_internship_android_kutsyy.app.data.model.mealdetails.RemoteMealDetails
import com.nikita.kut.android.epam_internship_android_kutsyy.feature.mealdetails.model.MealDetailsUIModel
import com.nikita.kut.android.epam_internship_android_kutsyy.feature.meallist.model.CategoryUIModel
import com.nikita.kut.android.epam_internship_android_kutsyy.feature.meallist.model.MealUIModel

fun RemoteMealList.toListMealUIModel(): List<MealUIModel> {
    val mealList = mutableListOf<MealUIModel>()
    for (i in this.meals) {
        mealList.add(MealUIModel(i.id, i.mealPicture, i.name))
    }
    return mealList
}

fun RemoteCategoryList.toListCategoryUIModel(): List<CategoryUIModel> {
    val categoryList = mutableListOf<CategoryUIModel>()
    for (i in this.categories) {
        categoryList.add(CategoryUIModel(i.id, i.categoryName, i.categoryPicture))
    }
    return categoryList.toList()
}

fun RemoteMealDetails.toMealDetailsUIModel(): MealDetailsUIModel {
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
    return MealDetailsUIModel(
        id = id,
        mealName = mealName,
        mealCategory = mealCategory,
        mealArea = mealArea,
        mealPicture = mealPicture,
        mealTags = mealTag?.split(",") ?: listOf(),
        mealIngredients = ingredients
    )
}