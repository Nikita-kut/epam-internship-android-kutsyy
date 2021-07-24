package com.nikita.kut.android.epam_internship_android_kutsyy.app.repository

import android.content.Context
import androidx.room.Room
import androidx.room.rxjava3.EmptyResultSetException
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
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MealRepository(context: Context) {

    private val database: AppDataBase =
        Room.databaseBuilder(context, AppDataBase::class.java, AppDataBase.DB_NAME).build()

    fun fetchMealList(
        categoryName: String
    ): Single<List<MealUIModel>> = RetrofitClient.retrofitService.getMeals(categoryName)
        .map { remoteMealList ->
            remoteMealList.toListMealUIModel()
        }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun fetchMealDetails(
        mealId: Int
    ): Single<MealDetailsUIModel> = database.getMealDetailsDao().getMealDetails(mealId)
        .onErrorResumeNext { error ->
            if (error is EmptyResultSetException) {
                RetrofitClient.retrofitService.getMealDetails(mealId)
                    .map { mealDetailsRemoteModel ->
                        mealDetailsRemoteModel.mealDetails.first().toDbMealDetailsModel()
                    }
                    .flatMap { mealDetailsDbModel ->
                        updateMealDetailsInDb(mealDetailsDbModel)
                            .toSingleDefault(mealDetailsDbModel)
                    }
            } else Single.error(error)
        }
        .map { mealDetailsDbModel -> mealDetailsDbModel.toMealDetailsUIModel() }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    private fun updateMealDetailsInDb(dbMealDetails: DbMealDetailsModel): Completable =
        database.getMealDetailsDao().updateMealDetails(dbMealDetails)
}