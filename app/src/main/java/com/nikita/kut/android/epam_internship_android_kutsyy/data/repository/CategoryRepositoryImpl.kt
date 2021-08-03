package com.nikita.kut.android.epam_internship_android_kutsyy.data.repository

import com.nikita.kut.android.epam_internship_android_kutsyy.data.db.AppDataBase
import com.nikita.kut.android.epam_internship_android_kutsyy.data.mapper.db.toListDbCategoryModel
import com.nikita.kut.android.epam_internship_android_kutsyy.data.model.db.CategoryDB
import com.nikita.kut.android.epam_internship_android_kutsyy.data.network.MealsApi
import com.nikita.kut.android.epam_internship_android_kutsyy.domain.mapper.toListCategoryEntity
import com.nikita.kut.android.epam_internship_android_kutsyy.domain.model.CategoryEntity
import com.nikita.kut.android.epam_internship_android_kutsyy.domain.repository.CategoryRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class CategoryRepositoryImpl(
    private val retrofitApi: MealsApi,
    private val dataBase: AppDataBase
) : CategoryRepository {

    override fun fetchCategoryList(): Single<List<CategoryEntity>> =
        dataBase.getCategoryDao()
            .getCategories()
            .flatMap { categoriesDbModel ->
                if (categoriesDbModel.isEmpty()) {
                    retrofitApi.getCategories()
                        .map { remoteCategories -> remoteCategories.toListDbCategoryModel() }
                        .flatMap { categoryDbModels ->
                            updateCategoriesInDb(categoryDbModels)
                                .toSingleDefault(categoryDbModels)
                        }
                } else Single.just(categoriesDbModel)
            }
            .map { categoryDbModels -> categoryDbModels.toListCategoryEntity() }

    private fun updateCategoriesInDb(categoriesDbList: List<CategoryDB>): Completable =
        dataBase.getCategoryDao()
            .updateCategories(categoriesDbList)

}