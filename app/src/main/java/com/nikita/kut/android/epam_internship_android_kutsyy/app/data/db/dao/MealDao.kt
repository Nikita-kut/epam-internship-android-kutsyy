package com.nikita.kut.android.epam_internship_android_kutsyy.app.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.nikita.kut.android.epam_internship_android_kutsyy.app.data.db.model.DbMealModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface MealDao {

    @Query("SELECT * FROM ${DbMealModel.TABLE_NAME} WHERE meal_name = :queryCategoryName")
    fun getMeals(queryCategoryName: String): Single<List<DbMealModel>>

    @Insert
    fun updateMeal(meals: List<DbMealModel>): Completable
}