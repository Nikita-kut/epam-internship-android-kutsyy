package com.nikita.kut.android.epam_internship_android_kutsyy.domain.usecase

import com.nikita.kut.android.epam_internship_android_kutsyy.domain.model.MealEntity
import com.nikita.kut.android.epam_internship_android_kutsyy.domain.repository.MealRepository
import com.nikita.kut.android.epam_internship_android_kutsyy.presentation.mapper.toListMealUI
import com.nikita.kut.android.epam_internship_android_kutsyy.presentation.model.MealUI
import io.reactivex.rxjava3.core.Single

class FetchMealListUseCase(private val repository: MealRepository) {

    fun fetchMealList(categoryName: String): Single<List<MealEntity>> =
        repository.fetchMealList(categoryName)
}