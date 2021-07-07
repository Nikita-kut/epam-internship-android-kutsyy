package com.nikita.kut.android.epam_internship_android_kutsyy.feature.meallist.presentation.adapter.meal

import androidx.recyclerview.widget.DiffUtil
import com.nikita.kut.android.epam_internship_android_kutsyy.app.data.model.meal.RemoteMeal
import com.nikita.kut.android.epam_internship_android_kutsyy.feature.meallist.model.MealUIModel

class MealDiffUtilCallback : DiffUtil.ItemCallback<MealUIModel>() {
    override fun areItemsTheSame(oldItem: MealUIModel, newItem: MealUIModel): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: MealUIModel, newItem: MealUIModel): Boolean = oldItem == newItem
}