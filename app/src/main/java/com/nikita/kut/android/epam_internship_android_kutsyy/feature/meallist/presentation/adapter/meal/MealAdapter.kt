package com.nikita.kut.android.epam_internship_android_kutsyy.feature.meallist.presentation.adapter.meal

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.nikita.kut.android.epam_internship_android_kutsyy.R
import com.nikita.kut.android.epam_internship_android_kutsyy.app.util.inflate
import com.nikita.kut.android.epam_internship_android_kutsyy.feature.meallist.model.Meal

class MealAdapter : RecyclerView.Adapter<MealHolder>() {

    private val differ = AsyncListDiffer(this, MealDiffUtilCallback())

    private lateinit var mListener: OnMealItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealHolder =
        MealHolder(
            parent.inflate(R.layout.item_meal)
        )

    override fun onBindViewHolder(holder: MealHolder, position: Int) {
        holder.bind(differ.currentList[position], mListener)
    }

    override fun getItemCount(): Int = differ.currentList.size

    fun updateList(newMeal: List<Meal>) {
        differ.submitList(newMeal)
    }

    fun setClickListener(listener: OnMealItemClickListener) {
        mListener = listener
    }

    interface OnMealItemClickListener {
        fun onItemClick(meal: Meal)
    }
}