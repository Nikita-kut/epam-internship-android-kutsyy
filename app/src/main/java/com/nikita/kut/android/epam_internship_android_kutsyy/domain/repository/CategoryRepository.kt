package com.nikita.kut.android.epam_internship_android_kutsyy.domain.repository

import com.nikita.kut.android.epam_internship_android_kutsyy.domain.model.CategoryEntity
import io.reactivex.rxjava3.core.Single

interface CategoryRepository {

    fun fetchCategoryList(): Single<List<CategoryEntity>>
}