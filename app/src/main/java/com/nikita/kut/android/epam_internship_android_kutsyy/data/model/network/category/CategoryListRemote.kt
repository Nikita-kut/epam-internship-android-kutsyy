package com.nikita.kut.android.epam_internship_android_kutsyy.data.model.network.category

import com.google.gson.annotations.SerializedName

data class CategoryListRemote(
    @SerializedName(value = "categories")
    val categories: List<CategoryRemote>
)
