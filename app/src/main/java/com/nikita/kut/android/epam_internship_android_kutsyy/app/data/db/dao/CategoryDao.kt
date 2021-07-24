package com.nikita.kut.android.epam_internship_android_kutsyy.app.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.nikita.kut.android.epam_internship_android_kutsyy.app.data.db.model.DbCategoryModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface CategoryDao {

    @Query("SELECT * FROM ${DbCategoryModel.TABLE_NAME} ")
    fun getCategories(): Single<List<DbCategoryModel>>

    @Insert
    fun updateCategories(categories: List<DbCategoryModel>): Completable
}