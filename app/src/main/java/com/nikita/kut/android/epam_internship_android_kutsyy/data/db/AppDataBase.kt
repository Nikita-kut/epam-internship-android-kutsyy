package com.nikita.kut.android.epam_internship_android_kutsyy.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nikita.kut.android.epam_internship_android_kutsyy.data.db.AppDataBase.Companion.DB_VERSION
import com.nikita.kut.android.epam_internship_android_kutsyy.data.db.converters.ListTagsConverter
import com.nikita.kut.android.epam_internship_android_kutsyy.data.db.dao.CategoryDao
import com.nikita.kut.android.epam_internship_android_kutsyy.data.db.dao.MealDao
import com.nikita.kut.android.epam_internship_android_kutsyy.data.db.dao.MealDetailsDao
import com.nikita.kut.android.epam_internship_android_kutsyy.data.model.db.CategoryDB
import com.nikita.kut.android.epam_internship_android_kutsyy.data.model.db.MealDB
import com.nikita.kut.android.epam_internship_android_kutsyy.data.model.db.MealDetailsDB

@Database(
    entities = [MealDB::class, CategoryDB::class, MealDetailsDB::class],
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