package com.nikita.kut.android.epam_internship_android_kutsyy.feature.meallist.presentation.adapter.meal

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.nikita.kut.android.epam_internship_android_kutsyy.R
import com.nikita.kut.android.epam_internship_android_kutsyy.app.util.inflate
import com.nikita.kut.android.epam_internship_android_kutsyy.feature.meallist.model.MealUIModel

class MealAdapter : RecyclerView.Adapter<MealViewHolder>() {

    private val differ = AsyncListDiffer(this, MealDiffUtilCallback())

    private lateinit var mListener: OnMealItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder =
        MealViewHolder(
            parent.inflate(R.layout.item_meal)
        )

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        holder.bind(differ.currentList[position], mListener)
    }

    override fun getItemCount(): Int = differ.currentList.size

    fun updateList(newMeal: List<MealUIModel>) {
        differ.submitList(newMeal)
    }

    fun setClickListener(listener: OnMealItemClickListener) {
        mListener = listener
    }

    interface OnMealItemClickListener {
        fun onItemClick(meal: MealUIModel)
    }
}