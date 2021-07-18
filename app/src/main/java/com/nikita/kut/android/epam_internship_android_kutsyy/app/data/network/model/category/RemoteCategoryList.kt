package com.nikita.kut.android.epam_internship_android_kutsyy.app.data.network.model.category

import com.google.gson.annotations.SerializedName

data class RemoteCategoryList(
    @SerializedName(value = "categories")
    val categories: List<RemoteCategory>
)
