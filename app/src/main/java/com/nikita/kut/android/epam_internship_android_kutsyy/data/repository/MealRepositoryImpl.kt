package com.nikita.kut.android.epam_internship_android_kutsyy.data.repository

import androidx.room.rxjava3.EmptyResultSetException
import com.nikita.kut.android.epam_internship_android_kutsyy.data.db.AppDataBase
import com.nikita.kut.android.epam_internship_android_kutsyy.data.mapper.db.toDbMealDetailsModel
import com.nikita.kut.android.epam_internship_android_kutsyy.data.model.db.MealDetailsDB
import com.nikita.kut.android.epam_internship_android_kutsyy.data.network.MealsApi
import com.nikita.kut.android.epam_internship_android_kutsyy.domain.mapper.toListMealEntity
import com.nikita.kut.android.epam_internship_android_kutsyy.domain.mapper.toMealDetailsEntity
import com.nikita.kut.android.epam_internship_android_kutsyy.domain.model.MealDetailsEntity
import com.nikita.kut.android.epam_internship_android_kutsyy.domain.model.MealEntity
import com.nikita.kut.android.epam_internship_android_kutsyy.domain.repository.MealRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class MealRepositoryImpl(
    private val retrofitApi: MealsApi,
    private val database: AppDataBase
) : MealRepository {

    override fun fetchMealList(categoryName: String): Single<List<MealEntity>> =
        retrofitApi.getMeals(categoryName)
            .map { remoteMealList -> remoteMealList.toListMealEntity() }

    override fun fetchMealDetails(mealId: Int): Single<MealDetailsEntity> =
        database.getMealDetailsDao().getMealDetails(mealId)
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
            .map { mealDetailsDbModel -> mealDetailsDbModel.toMealDetailsEntity() }

    private fun updateMealDetailsInDb(dbMealDetails: MealDetailsDB): Completable =
        database.getMealDetailsDao().updateMealDetails(dbMealDetails)
}