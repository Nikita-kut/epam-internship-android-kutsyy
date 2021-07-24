package com.nikita.kut.android.epam_internship_android_kutsyy.data.repository

import android.content.Context
import androidx.room.Room
import androidx.room.rxjava3.EmptyResultSetException
import com.nikita.kut.android.epam_internship_android_kutsyy.data.db.AppDataBase
import com.nikita.kut.android.epam_internship_android_kutsyy.data.model.db.MealDetailsEntity
import com.nikita.kut.android.epam_internship_android_kutsyy.data.network.RetrofitApi
import com.nikita.kut.android.epam_internship_android_kutsyy.data.network.RetrofitClient
import com.nikita.kut.android.epam_internship_android_kutsyy.domain.repository.MealRepository
import com.nikita.kut.android.epam_internship_android_kutsyy.util.toDbMealDetailsModel
import com.nikita.kut.android.epam_internship_android_kutsyy.util.toListMealUIModel
import com.nikita.kut.android.epam_internship_android_kutsyy.util.toMealDetailsUIModel
import com.nikita.kut.android.epam_internship_android_kutsyy.presentation.model.MealDetailsUI
import com.nikita.kut.android.epam_internship_android_kutsyy.presentation.model.MealUI
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class MealRepositoryImpl(
    private val dataBase: AppDataBase,
    private val retrofitApi: RetrofitApi
) : MealRepository {

    override fun fetchMealList(
        categoryName: String
    ): Single<List<MealUI>> = retrofitApi.getMeals(categoryName)
        .map { remoteMealList ->
            remoteMealList.toListMealUIModel()
        }
//        .subscribeOn(Schedulers.io())
//        .observeOn(AndroidSchedulers.mainThread())

    override fun fetchMealDetails(
        mealId: Int
    ): Single<MealDetailsUI> = dataBase.getMealDetailsDao().getMealDetails(mealId)
        .onErrorResumeNext { error ->
            if (error is EmptyResultSetException) {
                retrofitApi.getMealDetails(mealId)
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
//        .subscribeOn(Schedulers.io())
//        .observeOn(AndroidSchedulers.mainThread())

    private fun updateMealDetailsInDb(dbMealDetails: MealDetailsEntity): Completable =
        dataBase.getMealDetailsDao().updateMealDetails(dbMealDetails)
}