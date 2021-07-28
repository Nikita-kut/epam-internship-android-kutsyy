package com.nikita.kut.android.epam_internship_android_kutsyy.domain.usecase

import com.nikita.kut.android.epam_internship_android_kutsyy.domain.model.MealDetailsEntity
import com.nikita.kut.android.epam_internship_android_kutsyy.domain.repository.MealRepository
import com.nikita.kut.android.epam_internship_android_kutsyy.presentation.mapper.toMealDetailsUI
import com.nikita.kut.android.epam_internship_android_kutsyy.presentation.model.MealDetailsUI
import io.reactivex.rxjava3.core.Single

class FetchMealDetailsUseCase(private val repository: MealRepository) {

    fun fetchMealDetails(mealId: Int): Single<MealDetailsEntity> =
        repository.fetchMealDetails(mealId)
}