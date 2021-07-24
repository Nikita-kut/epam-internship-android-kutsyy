package com.nikita.kut.android.epam_internship_android_kutsyy.presentation.feature.meallist.view.adapter.meal

import androidx.recyclerview.widget.DiffUtil
import com.nikita.kut.android.epam_internship_android_kutsyy.presentation.model.MealUI

class MealDiffUtilCallback : DiffUtil.ItemCallback<MealUI>() {
    override fun areItemsTheSame(oldItem: MealUI, newItem: MealUI): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: MealUI, newItem: MealUI): Boolean = oldItem == newItem
}