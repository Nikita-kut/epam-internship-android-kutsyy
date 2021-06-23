package com.nikita.kut.android.epam_internship_android_kutsyy.feature.meallist.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.nikita.kut.android.epam_internship_android_kutsyy.R
import com.nikita.kut.android.epam_internship_android_kutsyy.feature.meallist.model.Category

class CategoryAdapter : RecyclerView.Adapter<CategoryHolder>() {

    private val differ = AsyncListDiffer(this, CategoryDiffUtilCallback())


    private lateinit var listener: OnCategoryItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder =
        CategoryHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_category, parent, false)
        )


    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.bind(differ.currentList[position], listener)
    }

    override fun getItemCount(): Int = differ.currentList.size

    fun updateCategoryList(newList: List<Category>) {
        differ.submitList(newList)
    }

    fun setClickListener(listener: OnCategoryItemClickListener) {
        this.listener = listener
    }

    interface OnCategoryItemClickListener {
        fun onCategoryClick(category: Category)
    }
}