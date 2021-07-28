package com.nikita.kut.android.epam_internship_android_kutsyy.data.model.network.category

import com.google.gson.annotations.SerializedName

data class CategoryRemote(
    @SerializedName(value = "idCategory")
    val id: Int,
    @SerializedName(value = "strCategory")
    val categoryName: String,
    @SerializedName(value = "strCategoryThumb")
    val categoryPicture: String,
)