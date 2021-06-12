package com.nikita.kut.android.epam_internship_android_kutsyy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.nikita.kut.android.epam_internship_android_kutsyy.R
import com.nikita.kut.android.epam_internship_android_kutsyy.model.Meal

class Adapter(private val listener: OnMealItemClick) : RecyclerView.Adapter<MealHolder>() {

    private val differ = AsyncListDiffer(this, MealDiffUtilCallback())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_meal, parent, false)
        return MealHolder(view)
    }

    override fun onBindViewHolder(holder: MealHolder, position: Int) {
        holder.bind(differ.currentList[position], listener)
    }

    override fun getItemCount(): Int = differ.currentList.size

    fun updateList(newMeal: List<Meal>) {
        differ.submitList(newMeal)
    }

    interface OnMealItemClick {
        fun onItemClick(position: Int)
    }
}