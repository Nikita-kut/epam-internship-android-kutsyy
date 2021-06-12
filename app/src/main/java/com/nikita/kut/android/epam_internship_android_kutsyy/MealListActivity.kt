package com.nikita.kut.android.epam_internship_android_kutsyy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nikita.kut.android.epam_internship_android_kutsyy.databinding.ActivityMealListBinding

class MealListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMealListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}