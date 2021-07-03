package com.nikita.kut.android.epam_internship_android_kutsyy.feature.meallist.presentation.adapter.meal

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.nikita.kut.android.epam_internship_android_kutsyy.app.util.setImage
import com.nikita.kut.android.epam_internship_android_kutsyy.databinding.ItemMealBinding
import com.nikita.kut.android.epam_internship_android_kutsyy.feature.meallist.model.MealUIModel

class MealViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemMealBinding.bind(view)
    lateinit var listener: MealAdapter.OnMealItemClickListener

    fun bind(meal: MealUIModel, listener: MealAdapter.OnMealItemClickListener) {
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