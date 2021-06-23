package com.nikita.kut.android.epam_internship_android_kutsyy.feature.meallist.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nikita.kut.android.epam_internship_android_kutsyy.feature.mealdetails.presentation.MealDetailsFragment
import com.nikita.kut.android.epam_internship_android_kutsyy.R
import com.nikita.kut.android.epam_internship_android_kutsyy.feature.meallist.presentation.adapter.MealAdapter
import com.nikita.kut.android.epam_internship_android_kutsyy.databinding.FragmentMealListBinding
import com.nikita.kut.android.epam_internship_android_kutsyy.feature.meallist.model.Category
import com.nikita.kut.android.epam_internship_android_kutsyy.feature.meallist.model.Meal
import com.nikita.kut.android.epam_internship_android_kutsyy.feature.meallist.presentation.adapter.CategoryAdapter

class MealListFragment : Fragment(), MealAdapter.OnMealItemClickListener,
    CategoryAdapter.OnCategoryItemClickListener {

    private lateinit var binding: FragmentMealListBinding

    private val mealAdapter: MealAdapter
        get() = binding.rvMealList.adapter as MealAdapter

    private val categoryAdapter: CategoryAdapter
        get() = binding.rvMealCategory.adapter as CategoryAdapter

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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMealListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRVMealList()
        initRVCategoryList()
    }


    private fun initRVMealList() {
        with(binding.rvMealList) {
            adapter = MealAdapter()
            mealAdapter.setClickListener(this@MealListFragment)
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
        mealAdapter.updateList(meals)
    }

    private fun initRVCategoryList() {
        with(binding.rvMealCategory) {
            adapter = CategoryAdapter()
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