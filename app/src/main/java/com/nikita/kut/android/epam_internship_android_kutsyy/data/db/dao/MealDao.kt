package com.nikita.kut.android.epam_internship_android_kutsyy.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.nikita.kut.android.epam_internship_android_kutsyy.data.model.db.MealEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface MealDao {

    @Query("SELECT * FROM ${MealEntity.TABLE_NAME} WHERE meal_name = :queryCategoryName")
    fun getMeals(queryCategoryName: String): Single<List<MealEntity>>

    @Insert
    fun updateMeal(meals: List<MealEntity>): Completable
}