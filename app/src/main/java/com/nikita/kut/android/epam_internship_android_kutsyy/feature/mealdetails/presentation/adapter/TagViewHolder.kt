package com.nikita.kut.android.epam_internship_android_kutsyy.feature.mealdetails.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.nikita.kut.android.epam_internship_android_kutsyy.databinding.ItemTagBinding

class TagViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemTagBinding.bind(view)

    fun bind(tag: String) {
        binding.tvMealTag.text = tag
    }

}