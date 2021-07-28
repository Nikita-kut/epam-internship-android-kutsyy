package com.nikita.kut.android.epam_internship_android_kutsyy.presentation.feature.meallist.view.adapter.meal

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.nikita.kut.android.epam_internship_android_kutsyy.util.setImage
import com.nikita.kut.android.epam_internship_android_kutsyy.databinding.ItemMealBinding
import com.nikita.kut.android.epam_internship_android_kutsyy.presentation.model.MealUI

class MealViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemMealBinding.bind(view)
    lateinit var listener: MealAdapter.OnMealItemClickListener

    fun bind(meal: MealUI, listener: MealAdapter.OnMealItemClickListener) {
        with(binding) {
            ivItemMealPicture.setImage(meal.mealPicture)
            tvItemMealName.text = meal.name
            this@MealViewHolder.listener = listener
            root.setOnClickListener {
                listener.onItemClick(meal)
            }
        }
    }


}