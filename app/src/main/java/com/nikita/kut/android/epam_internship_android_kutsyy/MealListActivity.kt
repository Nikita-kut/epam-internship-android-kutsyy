package com.nikita.kut.android.epam_internship_android_kutsyy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikita.kut.android.epam_internship_android_kutsyy.adapter.Adapter
import com.nikita.kut.android.epam_internship_android_kutsyy.databinding.ActivityMealListBinding
import com.nikita.kut.android.epam_internship_android_kutsyy.model.Meal

class MealListActivity : AppCompatActivity(), Adapter.OnMealItemClick {

    private lateinit var binding: ActivityMealListBinding
    private val adapter: Adapter
        get() = binding.rvMealList.adapter as Adapter
    private val meals: List<Meal> = listOf(
        Meal(
            mealPicture = R.drawable.meal_example_1,
            name = "Soy-Glazed Meatloaves with Wasabi Mashed Potatoes & Roasted Carrots",
            category = "JAPANESE",
            tag = "Soup"
        ),
        Meal(
            mealPicture = R.drawable.meal_example2,
            name = "Steak Diane",
            category = "USA",
            tag = "Meat"
        ),
        Meal(
            mealPicture = R.drawable.meal_example_1,
            name = "Soy-Glazed Meatloaves with Wasabi Mashed Potatoes & Roasted Carrots",
            category = "JAPANESE",
            tag = "Meat"
        ),
        Meal(
            mealPicture = R.drawable.meal_example2,
            name = "Steak Diane",
            category = "USA",
            tag = "Meat"
        ),
        Meal(
            mealPicture = R.drawable.meal_example_1,
            name = "Steak Diane",
            category = "USA",
            tag = "Meat"
        ),
        Meal(
            mealPicture = R.drawable.meal_example2,
            name = "Soy-Glazed Meatloaves with Wasabi Mashed Potatoes & Roasted Carrots",
            category = "JAPANESE",
            tag = "Meat"
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setRecyclerView()

    }

    private fun setRecyclerView() {
        with(binding.rvMealList) {
            adapter = Adapter(listener = this@MealListActivity)
            layoutManager = LinearLayoutManager(this@MealListActivity)
            setHasFixedSize(true)
        }
        adapter.updateList(meals)
    }

    override fun onItemClick(position: Int) {
        startActivity(MealDetailsActivity.getIntent(this, meal = meals[position]))
    }
}