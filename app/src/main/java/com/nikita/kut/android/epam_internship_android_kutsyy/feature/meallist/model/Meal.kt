package com.nikita.kut.android.epam_internship_android_kutsyy.feature.meallist.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize
import kotlin.random.Random

@Parcelize
data class Meal(
    val id: Int = Random.nextInt(),
    @DrawableRes val mealPicture: Int,
    val name: String,
    val category: String,
    val tag: String
): Parcelable