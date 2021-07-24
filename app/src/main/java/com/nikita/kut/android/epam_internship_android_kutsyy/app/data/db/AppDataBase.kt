package com.nikita.kut.android.epam_internship_android_kutsyy.app.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.nikita.kut.android.epam_internship_android_kutsyy.app.data.db.AppDataBase.Companion.DB_VERSION
import com.nikita.kut.android.epam_internship_android_kutsyy.app.data.db.converters.ListTagsConverter
import com.nikita.kut.android.epam_internship_android_kutsyy.app.data.db.dao.CategoryDao
import com.nikita.kut.android.epam_internship_android_kutsyy.app.data.db.dao.MealDao
import com.nikita.kut.android.epam_internship_android_kutsyy.app.data.db.dao.MealDetailsDao
import com.nikita.kut.android.epam_internship_android_kutsyy.app.data.db.model.DbCategoryModel
import com.nikita.kut.android.epam_internship_android_kutsyy.app.data.db.model.DbMealDetailsModel
import com.nikita.kut.android.epam_internship_android_kutsyy.app.data.db.model.DbMealModel

@Database(
    entities = [DbMealModel::class, DbCategoryModel::class, DbMealDetailsModel::class],
    version = DB_VERSION,
    exportSchema = false
)
@TypeConverters(ListTagsConverter::class)
abstract class AppDataBase : RoomDatabase() {

    abstract fun getCategoryDao(): CategoryDao

    abstract fun getMealDao(): MealDao

    abstract fun getMealDetailsDao(): MealDetailsDao

    companion object {
        const val DB_VERSION = 1
        const val DB_NAME = "app-database"
    }

}