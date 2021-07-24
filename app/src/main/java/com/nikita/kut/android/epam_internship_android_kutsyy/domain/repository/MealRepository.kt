package com.nikita.kut.android.epam_internship_android_kutsyy.domain.repository

import com.nikita.kut.android.epam_internship_android_kutsyy.presentation.model.MealDetailsUI
import com.nikita.kut.android.epam_internship_android_kutsyy.presentation.model.MealUI
import io.reactivex.rxjava3.core.Single

interface MealRepository {
    fun fetchMealList(categoryName: String): Single<List<MealUI>>

    fun fetchMealDetails(mealId: Int): Single<MealDetailsUI>
}