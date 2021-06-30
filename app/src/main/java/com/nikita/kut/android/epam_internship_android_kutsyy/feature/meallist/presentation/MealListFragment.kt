package com.nikita.kut.android.epam_internship_android_kutsyy.feature.meallist.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nikita.kut.android.epam_internship_android_kutsyy.R
import com.nikita.kut.android.epam_internship_android_kutsyy.app.repository.MealListRepository
import com.nikita.kut.android.epam_internship_android_kutsyy.app.util.AutoClearedValue
import com.nikita.kut.android.epam_internship_android_kutsyy.app.util.ViewBindingFragment
import com.nikita.kut.android.epam_internship_android_kutsyy.databinding.FragmentMealListBinding
import com.nikita.kut.android.epam_internship_android_kutsyy.feature.mealdetails.presentation.MealDetailsFragment
import com.nikita.kut.android.epam_internship_android_kutsyy.feature.meallist.model.Category
import com.nikita.kut.android.epam_internship_android_kutsyy.feature.meallist.model.CategoryList
import com.nikita.kut.android.epam_internship_android_kutsyy.feature.meallist.model.Meal
import com.nikita.kut.android.epam_internship_android_kutsyy.feature.meallist.model.MealList
import com.nikita.kut.android.epam_internship_android_kutsyy.feature.meallist.presentation.adapter.category.CategoryAdapter
import com.nikita.kut.android.epam_internship_android_kutsyy.feature.meallist.presentation.adapter.meal.MealAdapter

class MealListFragment :
    ViewBindingFragment<FragmentMealListBinding>(FragmentMealListBinding::inflate),
    MealAdapter.OnMealItemClickListener,
    CategoryAdapter.OnCategoryItemClickListener {

    private val repository = MealListRepository()

    private var mealAdapter by AutoClearedValue<MealAdapter>()

    private var categoryAdapter by AutoClearedValue<CategoryAdapter>()

    private lateinit var meals: MealList

    private lateinit var categories: CategoryList

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCategoriesFromNetwork()
        initRVMealList()
    }

    private fun initCategoriesFromNetwork() {
        repository.initMealCategoriesFromNetwork(
            onComplete = { categoryList ->
                this.categories = categoryList
                initRVCategoryList()
            },
            onError = { t ->
                Log.e("Server", "enqueue request error = ${t.message}", t)
                toast(resources.getString(R.string.load_error))
            }
        )
    }

    private fun initRVCategoryList() {
        categoryAdapter = CategoryAdapter()
        with(binding.rvMealCategory) {
            adapter = categoryAdapter
            categoryAdapter.setClickListener(this@MealListFragment)
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            setHasFixedSize(true)
        }
        categoryAdapter.updateCategoryList(categories.categories)
    }

    private fun initRVMealList() {
        mealAdapter = MealAdapter()
        with(binding.rvMealList) {
            adapter = mealAdapter
            mealAdapter.setClickListener(this@MealListFragment)
            layoutManager = LinearLayoutManager(requireContext())
            val itemDecoration =
                DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
            itemDecoration.setDrawable(resources.getDrawable(R.drawable.meal_list_divider))
            addItemDecoration(itemDecoration)
            setHasFixedSize(true)
        }
    }

    override fun onCategoryClick(category: Category) {
        categories.categories.forEach { if (it != category) it.clicked = false }
        categoryAdapter.updateCategoryList(categories.categories)
        categoryAdapter.notifyDataSetChanged()
        repository.initMealListFromNetwork(
            categoryName = category.categoryName,
            onComplete = { mealList ->
                this.meals = mealList
                mealAdapter.updateList(meals.meals)
            },
            onError = { t ->
                Log.e("Server", "enqueue request error = ${t.message}", t)
                toast(resources.getString(R.string.load_error))
            }
        )
    }

    override fun onItemClick(meal: Meal) {
        openFragment(meal.id)
    }

    private fun openFragment(mealId: Int) {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_main_container, MealDetailsFragment.newInstance(mealId))
            .addToBackStack(null)
            .commit()
    }

    private fun toast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance(): MealListFragment = MealListFragment()
    }
}