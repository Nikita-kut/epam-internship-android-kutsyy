package com.nikita.kut.android.epam_internship_android_kutsyy.presentation.feature.mealdetails.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nikita.kut.android.epam_internship_android_kutsyy.R
import com.nikita.kut.android.epam_internship_android_kutsyy.util.inflate

class TagAdapter : RecyclerView.Adapter<TagViewHolder>() {

    private var tagList: List<String> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder =
        TagViewHolder(parent.inflate(R.layout.item_tag))

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
        holder.bind(tagList[position])
    }

    override fun getItemCount(): Int = tagList.size

    fun updateTagList(newTagList: List<String>) {
        tagList = newTagList
        notifyDataSetChanged()
    }
}