package com.nikita.kut.android.epam_internship_android_kutsyy.feature.meallist.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nikita.kut.android.epam_internship_android_kutsyy.R
import com.nikita.kut.android.epam_internship_android_kutsyy.databinding.ItemCategoryBinding
import com.nikita.kut.android.epam_internship_android_kutsyy.feature.meallist.model.Category

class CategoryHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemCategoryBinding.bind(view)
    lateinit var listener: CategoryAdapter.OnCategoryItemClickListener

    fun bind(category: Category, listener: CategoryAdapter.OnCategoryItemClickListener) {
        Glide.with(itemView)
            .load(category.categoryPicture)
            .dontAnimate()
            .into(binding.ivMealCategoryPicture)
        this.listener = listener
        binding.root.setOnClickListener {
            category.clicked = category.clicked.not()
            listener.onCategoryClick(category)
        }
        setItemBackgroundColor(category.clicked)
    }

    private fun setItemBackgroundColor(clicked: Boolean) {
        if (clicked) {
            binding.ivMealCategoryPicture.setBackgroundResource(R.drawable.meal_picture_selected_back)
        } else {
            binding.ivMealCategoryPicture.setBackgroundResource(R.drawable.meal_picture_back)
        }
    }
}