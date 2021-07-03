package com.nikita.kut.android.epam_internship_android_kutsyy.feature.meallist.presentation.adapter.category

import androidx.recyclerview.widget.DiffUtil
import com.nikita.kut.android.epam_internship_android_kutsyy.app.data.model.category.RemoteCategory
import com.nikita.kut.android.epam_internship_android_kutsyy.feature.meallist.model.CategoryUIModel

class CategoryDiffUtilCallback : DiffUtil.ItemCallback<CategoryUIModel>() {
    override fun areItemsTheSame(oldItem: CategoryUIModel, newItem: CategoryUIModel): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: CategoryUIModel, newItem: CategoryUIModel): Boolean =
        oldItem == newItem
}