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
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class CategoryRepository(context: Context) {

    private val database: AppDataBase =
        Room.databaseBuilder(context, AppDataBase::class.java, AppDataBase.DB_NAME).build()

    fun fetchCategoryList(
        onComplete: (List<CategoryUIModel>) -> Unit,
        onError: (Throwable) -> Unit
    ): Disposable {
        return database.getCategoryDao()
            .getCategories()
            .map { listDbCategoryModel ->
                listDbCategoryModel.toListCategoryUIModel()
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { categoryList ->
                    if (categoryList.isNotEmpty()) {
                        onComplete(categoryList)
                    } else {
                        RetrofitClient.retrofitService
                            .getCategories()
                            .map { remoteCategoryList ->
                                remoteCategoryList.toListDbCategoryModel()
                            }
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                { dbCategoryList ->
                                    updateCategoriesInDb(dbCategoryList)
                                    onComplete(dbCategoryList.toListCategoryUIModel())
                                },
                                { throwable ->
                                    onError(throwable)
                                })
                    }
                },
                { throwable ->
                    onError(throwable)
                })
    }

    private fun updateCategoriesInDb(categoriesDbList: List<DbCategoryModel>): Disposable {
        return database.getCategoryDao()
            .updateCategories(categoriesDbList)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }
}