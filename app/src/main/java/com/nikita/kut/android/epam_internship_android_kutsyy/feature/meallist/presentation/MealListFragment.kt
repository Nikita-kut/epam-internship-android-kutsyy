package com.nikita.kut.android.epam_internship_android_kutsyy.feature.meallist.presentation

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nikita.kut.android.epam_internship_android_kutsyy.R
import com.nikita.kut.android.epam_internship_android_kutsyy.app.util.AutoClearedValue
import com.nikita.kut.android.epam_internship_android_kutsyy.app.util.ViewBindingFragment
import com.nikita.kut.android.epam_internship_android_kutsyy.databinding.FragmentMealListBinding
import com.nikita.kut.android.epam_internship_android_kutsyy.feature.mealdetails.presentation.MealDetailsFragment
import com.nikita.kut.android.epam_internship_android_kutsyy.feature.meallist.model.Category
import com.nikita.kut.android.epam_internship_android_kutsyy.feature.meallist.model.Meal
import com.nikita.kut.android.epam_internship_android_kutsyy.feature.meallist.presentation.adapter.CategoryAdapter
import com.nikita.kut.android.epam_internship_android_kutsyy.feature.meallist.presentation.adapter.MealAdapter

class MealListFragment :
    ViewBindingFragment<FragmentMealListBinding>(FragmentMealListBinding::inflate),
    MealAdapter.OnMealItemClickListener,
    CategoryAdapter.OnCategoryItemClickListener {

    private var mealAdapter by AutoClearedValue<MealAdapter>()

    private var categoryAdapter by AutoClearedValue<CategoryAdapter>()

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

    private val categories: List<Category> = listOf(
        Category(categoryPicture = R.drawable.beef),
        Category(categoryPicture = R.drawable.desert),
        Category(categoryPicture = R.drawable.pizza),
        Category(categoryPicture = R.drawable.pita),
        Category(categoryPicture = R.drawable.chicken),
        )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRVMealList()
        initRVCategoryList()
    }


    private fun initRVMealList() {
        mealAdapter = MealAdapter()
        with(binding.rvMealList) {
            adapter = mealAdapter
            mealAdapter.setClickListener(this@MealListFragment)
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
        mealAdapter.updateList(meals)
    }

    private fun initRVCategoryList() {
        categoryAdapter = CategoryAdapter()
        with(binding.rvMealCategory) {
            adapter = categoryAdapter
            categoryAdapter.setClickListener(this@MealListFragment)
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            setHasFixedSize(true)
        }
        categoryAdapter.updateCategoryList(categories)
    }

    override fun onItemClick(meal: Meal) {
        openFragment(meal)
    }

    override fun onCategoryClick(category: Category) {
        //ToDo
    }

    private fun openFragment(meal: Meal) {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_main_container, MealDetailsFragment.newInstance(meal))
            .addToBackStack(null)
            .commit()
    }

    companion object {
        fun newInstance(): MealListFragment = MealListFragment()
    }
}