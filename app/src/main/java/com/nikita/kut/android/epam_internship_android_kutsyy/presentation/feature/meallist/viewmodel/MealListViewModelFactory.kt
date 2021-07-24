package com.nikita.kut.android.epam_internship_android_kutsyy.presentation.feature.meallist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nikita.kut.android.epam_internship_android_kutsyy.domain.usecase.FetchCategoryListUseCase
import com.nikita.kut.android.epam_internship_android_kutsyy.domain.usecase.FetchMealListUseCase

class MealListViewModelFactory(
    private val fetchCategoryListUseCase: FetchCategoryListUseCase,
    private val fetchMealListUseCase: FetchMealListUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        MealListViewModel(fetchCategoryListUseCase, fetchMealListUseCase) as T
}