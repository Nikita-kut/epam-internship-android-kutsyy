package com.nikita.kut.android.epam_internship_android_kutsyy

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nikita.kut.android.epam_internship_android_kutsyy.databinding.ActivityMealDetailsBinding
import com.nikita.kut.android.epam_internship_android_kutsyy.model.Meal

class MealDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMealDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setViews()
        binding.toolbarMealList.setNavigationOnClickListener { finish() }
    }

    private fun setViews() {
        if (intent != null && intent.hasExtra(MEAL_DETAIL_INTENT)) {
            val meal = intent.getParcelableExtra<Meal>(MEAL_DETAIL_INTENT) ?: error("")
            binding.tvMealCategory.text = meal.category
            binding.tvMealName.text = meal.name
            binding.tvMealTag.text = meal.tag
        }
    }


    companion object {
        private const val MEAL_DETAIL_INTENT = "MEAL_DETAIL_INTENT"
        fun getIntent(context: Context, meal: Meal): Intent =
            Intent(context, MealDetailsActivity::class.java).putExtra(MEAL_DETAIL_INTENT, meal)

    }
}