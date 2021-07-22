package com.nikita.kut.android.epam_internship_android_kutsyy.app.repository

import android.content.Context
import androidx.room.Room
import com.nikita.kut.android.epam_internship_android_kutsyy.app.data.db.AppDataBase
import com.nikita.kut.android.epam_internship_android_kutsyy.app.data.db.model.DbCategoryModel
import com.nikita.kut.android.epam_internship_android_kutsyy.app.data.network.RetrofitClient
import com.nikita.kut.android.epam_internship_android_kutsyy.app.util.toListCategoryUIModel
import com.nikita.kut.android.epam_internship_android_kutsyy.app.util.toListDbCategoryModel
import com.nikita.kut.android.epam_internship_android_kutsyy.feature.meallist.model.CategoryUIModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class CategoryRepository(context: Context) {

    private val database: AppDataBase =
        Room.databaseBuilder(context, AppDataBase::class.java, AppDataBase.DB_NAME).build()

    fun fetchCategoryList(
    ): Single<List<CategoryUIModel>> =
        database.getCategoryDao()
            .getCategories()
            .flatMap { categoriesDbModel ->
                if (categoriesDbModel.isEmpty()) {
                    RetrofitClient.retrofitService
                        .getCategories()
                        .map { remoteCategories -> remoteCategories.toListDbCategoryModel() }
                        .flatMap { categoryDbModels ->
                            updateCategoriesInDb(categoryDbModels)
                                .toSingleDefault(categoryDbModels)
                        }
                } else Single.just(categoriesDbModel)
            }
            .map { categoryDbModels -> categoryDbModels.toListCategoryUIModel() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    private fun updateCategoriesInDb(categoriesDbList: List<DbCategoryModel>): Completable =
        database.getCategoryDao()
            .updateCategories(categoriesDbList)

}