package com.nikita.kut.android.epam_internship_android_kutsyy.app.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nikita.kut.android.epam_internship_android_kutsyy.app.data.db.model.DbMealModel.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class DbMealModel(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "meal_picture") val mealPicture: String,
    @ColumnInfo(name = "meal_name") val name: String,
) {
    companion object {
        const val TABLE_NAME = "meal"
    }
}