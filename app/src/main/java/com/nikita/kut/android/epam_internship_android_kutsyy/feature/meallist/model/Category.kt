package com.nikita.kut.android.epam_internship_android_kutsyy.feature.meallist.model

import androidx.annotation.DrawableRes
import kotlin.random.Random

data class Category(
    val id: Int = Random.nextInt(),
    @DrawableRes val categoryPicture: Int,
    var clickCount: Int = 1,
)