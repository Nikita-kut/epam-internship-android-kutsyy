package com.nikita.kut.android.epam_internship_android_kutsyy.feature.meallist.presentation.adapter.category

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.nikita.kut.android.epam_internship_android_kutsyy.R
import com.nikita.kut.android.epam_internship_android_kutsyy.app.util.setImage
import com.nikita.kut.android.epam_internship_android_kutsyy.databinding.ItemCategoryBinding
import com.nikita.kut.android.epam_internship_android_kutsyy.feature.meallist.model.CategoryUIModel

class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemCategoryBinding.bind(view)
    lateinit var listener: CategoryAdapter.OnCategoryItemClickListener

    fun bind(category: CategoryUIModel, listener: CategoryAdapter.OnCategoryItemClickListener) {
        with(binding) {
            ivMealCategoryPicture.setImage(category.categoryPicture)
            this@CategoryViewHolder.listener = listener
            root.setOnClickListener {
                listener.onCategoryClick(category)
            }
            setItemBackgroundColor(category.isSelected)
        }
    }

    private fun setItemBackgroundColor(isSelected: Boolean) {
        if (isSelected) {
            binding.ivMealCategoryPicture.setBackgroundResource(R.drawable.meal_picture_selected_back)
        } else {
            binding.ivMealCategoryPicture.setBackgroundResource(R.drawable.meal_picture_back)
        }
    }
}