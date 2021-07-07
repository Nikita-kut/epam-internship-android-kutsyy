package com.nikita.kut.android.epam_internship_android_kutsyy.app.data.model.category

import com.google.gson.annotations.SerializedName
import com.nikita.kut.android.epam_internship_android_kutsyy.app.data.model.category.RemoteCategory

data class RemoteCategoryList(
    @SerializedName(value = "categories")
    val categories: List<RemoteCategory>
)
