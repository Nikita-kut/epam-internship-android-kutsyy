package com.nikita.kut.android.epam_internship_android_kutsyy.domain.repository

import com.nikita.kut.android.epam_internship_android_kutsyy.presentation.model.CategoryUI
import io.reactivex.rxjava3.core.Single

interface CategoryRepository {

    fun fetchCategoryList(): Single<List<CategoryUI>>
}