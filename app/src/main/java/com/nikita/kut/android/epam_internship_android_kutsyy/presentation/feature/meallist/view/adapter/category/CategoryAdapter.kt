package com.nikita.kut.android.epam_internship_android_kutsyy.presentation.feature.meallist.view.adapter.category

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nikita.kut.android.epam_internship_android_kutsyy.R
import com.nikita.kut.android.epam_internship_android_kutsyy.util.inflate
import com.nikita.kut.android.epam_internship_android_kutsyy.presentation.model.CategoryUI

class CategoryAdapter : RecyclerView.Adapter<CategoryViewHolder>() {

    private var categoryList: List<CategoryUI> = listOf()

    private lateinit var listener: OnCategoryItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder =
        CategoryViewHolder(
            parent.inflate(R.layout.item_category)
        )

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categoryList[position], listener)
    }

    override fun getItemCount(): Int =
        categoryList.size

    fun updateCategoryList(newList: List<CategoryUI>) {
        categoryList = newList
        notifyDataSetChanged()
    }

    fun setClickListener(listener: OnCategoryItemClickListener) {
        this.listener = listener
    }

    interface OnCategoryItemClickListener {
        fun onCategoryClick(category: CategoryUI)
    }
}