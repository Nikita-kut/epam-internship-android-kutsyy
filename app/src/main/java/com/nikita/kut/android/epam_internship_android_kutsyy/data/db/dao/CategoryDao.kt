package com.nikita.kut.android.epam_internship_android_kutsyy.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.nikita.kut.android.epam_internship_android_kutsyy.data.model.db.CategoryEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface CategoryDao {

    @Query("SELECT * FROM ${CategoryEntity.TABLE_NAME} ")
    fun getCategories(): Single<List<CategoryEntity>>

    @Insert
    fun updateCategories(categories: List<CategoryEntity>): Completable
}