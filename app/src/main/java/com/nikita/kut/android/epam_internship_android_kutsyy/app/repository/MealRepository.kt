package com.nikita.kut.android.epam_internship_android_kutsyy.app.repository

import android.content.Context
import androidx.room.Room
import com.nikita.kut.android.epam_internship_android_kutsyy.app.data.db.AppDataBase
import com.nikita.kut.android.epam_internship_android_kutsyy.app.data.db.model.DbCategoryModel
import com.nikita.kut.android.epam_internship_android_kutsyy.app.data.db.model.DbMealDetailsModel
import com.nikita.kut.android.epam_internship_android_kutsyy.app.data.db.model.DbMealModel
import com.nikita.kut.android.epam_internship_android_kutsyy.app.data.network.RetrofitClient
import com.nikita.kut.android.epam_internship_android_kutsyy.app.util.toDbMealDetailsModel
import com.nikita.kut.android.epam_internship_android_kutsyy.app.util.toListDbMealModel
import com.nikita.kut.android.epam_internship_android_kutsyy.app.util.toListMealUIModel
import com.nikita.kut.android.epam_internship_android_kutsyy.app.util.toMealDetailsUIModel
import com.nikita.kut.android.epam_internship_android_kutsyy.feature.mealdetails.model.MealDetailsUIModel
import com.nikita.kut.android.epam_internship_android_kutsyy.feature.meallist.model.MealUIModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MealRepository(context: Context) {

    private val database: AppDataBase =
        Room.databaseBuilder(context, AppDataBase::class.java, AppDataBase.DB_NAME).build()

    fun fetchMealList(
        categoryName: String,
        onComplete: (List<MealUIModel>) -> Unit,
        onError: (Throwable) -> Unit
    ): Disposable {
        return RetrofitClient.retrofitService.getMeals(categoryName)
            .map { remoteMealList ->
                remoteMealList.toListMealUIModel()
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { listMeals ->
                    onComplete(listMeals)
                },
                { throwable ->
                    onError(throwable)
                }
            )
    }

    fun fetchMealDetails(
        mealId: Int,
        onComplete: (MealDetailsUIModel) -> Unit,
        onError: (Throwable) -> Unit
    ): Disposable {
        return database.getMealDetailsDao()
            .getMealDetails(mealId)
            .map { dbMealDetailsModel ->
                dbMealDetailsModel.toMealDetailsUIModel()
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { mealDetailsModel ->
                    if (mealDetailsModel != null) {
                        onComplete(mealDetailsModel)
                    }
                },
                { throwable ->
                    RetrofitClient.retrofitService.getMealDetails(mealId)
                        .map { remoteMealDetails ->
                            remoteMealDetails.mealDetails.first().toDbMealDetailsModel()
                        }
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe { dbMealDetailsModel ->
                            updateMealDetails(dbMealDetailsModel)
                            onComplete(dbMealDetailsModel.toMealDetailsUIModel())
                        }
                    onError(throwable)
                }
            )
    }

    private fun updateMealDetails(dbMealDetails: DbMealDetailsModel): Disposable {
        return database.getMealDetailsDao().updateMealDetails(dbMealDetails)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }
}