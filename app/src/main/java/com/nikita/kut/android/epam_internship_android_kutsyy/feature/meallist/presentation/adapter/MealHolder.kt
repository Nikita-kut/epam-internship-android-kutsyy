package com.nikita.kut.android.epam_internship_android_kutsyy.feature.meallist.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.nikita.kut.android.epam_internship_android_kutsyy.databinding.ItemMealBinding
import com.nikita.kut.android.epam_internship_android_kutsyy.feature.meallist.model.Meal

class MealHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemMealBinding.bind(view)
    lateinit var listener: MealAdapter.OnMealItemClickListener

    fun bind(meal: Meal, listener: MealAdapter.OnMealItemClickListener) {
        Glide.with(itemView)
            .load(meal.mealPicture)
            .dontAnimate()
            .centerCrop()
            .into(binding.ivItemMealPicture)
        binding.tvItemMealName.text = meal.name
        this.listener = listener
        binding.root.setOnClickListener {
            listener.onItemClick(meal)
        }
    }


}