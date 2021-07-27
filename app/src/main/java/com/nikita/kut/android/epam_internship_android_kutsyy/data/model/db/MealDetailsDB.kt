package com.nikita.kut.android.epam_internship_android_kutsyy.data.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nikita.kut.android.epam_internship_android_kutsyy.data.model.db.MealDetailsDB.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class MealDetailsDB(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "meal_detail_name") val mealName: String,
    @ColumnInfo(name = "meal_detail_category") val mealCategory: String,
    @ColumnInfo(name = "meal_detail_area") val mealArea: String,
    @ColumnInfo(name = "meal_detail_picture") val mealPicture: String,
    @ColumnInfo(name = "meal_detail_tags") val mealTags: List<String>,
    @ColumnInfo(name = "meal_detail_ingredients") val mealIngredients: String
) {
    companion object {
        const val TABLE_NAME = "meal_details"
    }
}
