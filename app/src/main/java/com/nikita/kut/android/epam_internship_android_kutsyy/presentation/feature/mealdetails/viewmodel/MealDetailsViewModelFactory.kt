package com.nikita.kut.android.epam_internship_android_kutsyy.presentation.feature.mealdetails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nikita.kut.android.epam_internship_android_kutsyy.domain.usecase.FetchMealDetailsUseCase

class MealDetailsViewModelFactory(
    private val fetchMealDetailsUseCase: FetchMealDetailsUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        MealDetailsViewModel(fetchMealDetailsUseCase) as T
}