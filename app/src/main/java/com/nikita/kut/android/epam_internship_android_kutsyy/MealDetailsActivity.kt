package com.nikita.kut.android.epam_internship_android_kutsyy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nikita.kut.android.epam_internship_android_kutsyy.databinding.ActivityMealDetailsBinding

class MealDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMealDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}