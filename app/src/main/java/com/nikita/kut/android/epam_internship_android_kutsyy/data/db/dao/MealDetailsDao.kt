package com.nikita.kut.android.epam_internship_android_kutsyy.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.nikita.kut.android.epam_internship_android_kutsyy.data.model.db.MealDetailsEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface MealDetailsDao {

    @Query("SELECT * FROM ${MealDetailsEntity.TABLE_NAME} WHERE id = :mealId")
    fun getMealDetails(mealId: Int): Single<MealDetailsEntity>

    @Insert
    fun updateMealDetails(mealDetails: MealDetailsEntity): Completable
}