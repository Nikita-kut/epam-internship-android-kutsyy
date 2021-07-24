package com.nikita.kut.android.epam_internship_android_kutsyy.data.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nikita.kut.android.epam_internship_android_kutsyy.data.model.db.CategoryEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class CategoryEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo (name = "category_name") val categoryName: String,
    @ColumnInfo(name = "category_picture") val categoryPicture: String,
) {
    companion object {
        const val TABLE_NAME = "categories"
    }
}
