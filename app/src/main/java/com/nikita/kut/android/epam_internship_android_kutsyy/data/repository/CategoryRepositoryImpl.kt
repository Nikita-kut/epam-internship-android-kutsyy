package com.nikita.kut.android.epam_internship_android_kutsyy.data.repository

import com.nikita.kut.android.epam_internship_android_kutsyy.data.db.AppDataBase
import com.nikita.kut.android.epam_internship_android_kutsyy.data.model.db.CategoryEntity
import com.nikita.kut.android.epam_internship_android_kutsyy.data.network.RetrofitApi
import com.nikita.kut.android.epam_internship_android_kutsyy.domain.repository.CategoryRepository
import com.nikita.kut.android.epam_internship_android_kutsyy.presentation.model.CategoryUI
import com.nikita.kut.android.epam_internship_android_kutsyy.util.toListCategoryUIModel
import com.nikita.kut.android.epam_internship_android_kutsyy.util.toListDbCategoryModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class CategoryRepositoryImpl(
    private val dataBase: AppDataBase,
    private val retrofitApi: RetrofitApi
) : CategoryRepository {

    override fun fetchCategoryList(
    ): Single<List<CategoryUI>> =
        dataBase.getCategoryDao()
            .getCategories()
            .flatMap { categoriesDbModel ->
                if (categoriesDbModel.isEmpty()) {
                    retrofitApi
                        .getCategories()
                        .map { remoteCategories -> remoteCategories.toListDbCategoryModel() }
                        .flatMap { categoryDbModels ->
                            updateCategoriesInDb(categoryDbModels)
                                .toSingleDefault(categoryDbModels)
                        }
                } else Single.just(categoriesDbModel)
            }
            .map { categoryDbModels -> categoryDbModels.toListCategoryUIModel() }
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())

    private fun updateCategoriesInDb(categoriesDbList: List<CategoryEntity>): Completable =
        dataBase.getCategoryDao()
            .updateCategories(categoriesDbList)

}