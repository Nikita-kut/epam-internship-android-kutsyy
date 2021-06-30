package com.nikita.kut.android.epam_internship_android_kutsyy.app.util

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.LayoutRes
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nikita.kut.android.epam_internship_android_kutsyy.R

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun ImageView.setImage(URI: String) {
    Glide.with(this.context)
        .load(URI)
        .apply(RequestOptions())
        .placeholder(R.drawable.loading_animation)
        .error(R.drawable.ic_add_photo)
        .into(this)
}

fun ImageView.setImageMealDetails(URI: String) {
    Glide.with(this.context)
        .load(URI)
        .apply(RequestOptions())
        .placeholder(R.drawable.loading_animation)
        .error(R.drawable.ic_add_photo)
        .into(this)
}