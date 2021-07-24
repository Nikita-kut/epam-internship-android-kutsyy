package com.nikita.kut.android.epam_internship_android_kutsyy.data.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nikita.kut.android.epam_internship_android_kutsyy.data.model.db.MealEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class MealEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "meal_picture") val mealPicture: String,
    @ColumnInfo(name = "meal_name") val name: String,
) {
    companion object {
        const val TABLE_NAME = "meal"
    }
}