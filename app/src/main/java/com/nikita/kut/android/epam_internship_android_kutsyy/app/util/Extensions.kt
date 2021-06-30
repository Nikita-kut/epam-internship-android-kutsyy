package com.nikita.kut.android.epam_internship_android_kutsyy.app.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.common.base.Joiner
import com.nikita.kut.android.epam_internship_android_kutsyy.R
import com.nikita.kut.android.epam_internship_android_kutsyy.feature.mealdetails.model.MealDetails
import com.nikita.kut.android.epam_internship_android_kutsyy.feature.mealdetails.model.MealDetailsUIModel

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun ImageView.setImage(URI: String) {
    Glide.with(this.context)
        .load(URI)
        .apply(RequestOptions())
        .placeholder(R.drawable.loading_animation)
        .error(R.drawable.ic_add_photo)
        .into(this)
}

fun MealDetails.getMealDetailsUIModel(): MealDetailsUIModel {
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
    val ingredients: String = Joiner.on(",\n").withKeyValueSeparator(" ").join(filteredIngredientMap)
    return MealDetailsUIModel(
        id = id,
        mealName = mealName,
        mealCategory = mealCategory,
        mealArea = mealArea,
        mealPicture = mealPicture,
        mealTag = mealTag,
        mealIngredients = ingredients
    )
}