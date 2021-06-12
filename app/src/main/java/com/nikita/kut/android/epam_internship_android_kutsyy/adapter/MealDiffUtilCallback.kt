package com.nikita.kut.android.epam_internship_android_kutsyy.adapter

import androidx.recyclerview.widget.DiffUtil
import com.nikita.kut.android.epam_internship_android_kutsyy.model.Meal

class MealDiffUtilCallback : DiffUtil.ItemCallback<Meal>() {
    override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean = oldItem == newItem
}