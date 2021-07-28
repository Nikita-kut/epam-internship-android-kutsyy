package com.nikita.kut.android.epam_internship_android_kutsyy.domain.usecase

import com.nikita.kut.android.epam_internship_android_kutsyy.domain.model.CategoryEntity
import com.nikita.kut.android.epam_internship_android_kutsyy.domain.repository.CategoryRepository
import com.nikita.kut.android.epam_internship_android_kutsyy.presentation.mapper.toListCategoryUI
import com.nikita.kut.android.epam_internship_android_kutsyy.presentation.model.CategoryUI
import io.reactivex.rxjava3.core.Single

class FetchCategoryListUseCase(private val repository: CategoryRepository) {

    fun fetchCategoryList(): Single<List<CategoryEntity>> =
        repository.fetchCategoryList()
}