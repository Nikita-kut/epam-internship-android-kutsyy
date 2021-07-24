package com.nikita.kut.android.epam_internship_android_kutsyy.app.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.nikita.kut.android.epam_internship_android_kutsyy.app.data.db.model.DbMealDetailsModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface MealDetailsDao {

    @Query("SELECT * FROM ${DbMealDetailsModel.TABLE_NAME} WHERE id = :mealId")
    fun getMealDetails(mealId: Int): Single<DbMealDetailsModel>

    @Insert
    fun updateMealDetails(mealDetails: DbMealDetailsModel): Completable
}