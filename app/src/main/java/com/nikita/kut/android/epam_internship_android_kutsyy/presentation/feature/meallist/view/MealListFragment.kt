package com.nikita.kut.android.epam_internship_android_kutsyy.presentation.feature.meallist.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.nikita.kut.android.epam_internship_android_kutsyy.R
import com.nikita.kut.android.epam_internship_android_kutsyy.data.db.AppDataBase
import com.nikita.kut.android.epam_internship_android_kutsyy.data.network.RetrofitClient
import com.nikita.kut.android.epam_internship_android_kutsyy.data.preference.SharedPreferenceModel
import com.nikita.kut.android.epam_internship_android_kutsyy.data.repository.CategoryRepositoryImpl
import com.nikita.kut.android.epam_internship_android_kutsyy.data.repository.MealRepositoryImpl
import com.nikita.kut.android.epam_internship_android_kutsyy.databinding.FragmentMealListBinding
import com.nikita.kut.android.epam_internship_android_kutsyy.domain.usecase.FetchCategoryListUseCase
import com.nikita.kut.android.epam_internship_android_kutsyy.domain.usecase.FetchMealListUseCase
import com.nikita.kut.android.epam_internship_android_kutsyy.presentation.feature.mealdetails.view.MealDetailsFragment
import com.nikita.kut.android.epam_internship_android_kutsyy.presentation.feature.meallist.view.adapter.category.CategoryAdapter
import com.nikita.kut.android.epam_internship_android_kutsyy.presentation.feature.meallist.view.adapter.meal.MealAdapter
import com.nikita.kut.android.epam_internship_android_kutsyy.presentation.feature.meallist.viewmodel.MealListViewModel
import com.nikita.kut.android.epam_internship_android_kutsyy.presentation.feature.meallist.viewmodel.MealListViewModelFactory
import com.nikita.kut.android.epam_internship_android_kutsyy.presentation.model.CategoryUI
import com.nikita.kut.android.epam_internship_android_kutsyy.presentation.model.MealUI
import com.nikita.kut.android.epam_internship_android_kutsyy.util.memory.AutoClearedValue
import com.nikita.kut.android.epam_internship_android_kutsyy.util.memory.ViewBindingFragment

class MealListFragment :
    ViewBindingFragment<FragmentMealListBinding>(FragmentMealListBinding::inflate),
    MealAdapter.OnMealItemClickListener,
    CategoryAdapter.OnCategoryItemClickListener {

    private val database by lazy {
        Room.databaseBuilder(requireContext(), AppDataBase::class.java, AppDataBase.DB_NAME).build()
    }

    private var mealAdapter by AutoClearedValue<MealAdapter>()

    private var categoryAdapter by AutoClearedValue<CategoryAdapter>()

    private var categories: List<CategoryUI>? = null

    private val viewModel: MealListViewModel by viewModels {
        MealListViewModelFactory(
            FetchCategoryListUseCase(CategoryRepositoryImpl(database, RetrofitClient.retrofitApi)),
            FetchMealListUseCase(MealRepositoryImpl(database, RetrofitClient.retrofitApi))
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        SharedPreferenceModel.with(requireActivity().application)
        initRVCategoryList()
        initCategoriesList()
        initRVMealList()
    }

    private fun initCategoriesList() {
        viewModel.startCategoryList()
        viewModel.categoryList.observe(viewLifecycleOwner) { categoryList ->
            this.categories = categoryList
            categoryAdapter.updateCategoryList(categories ?: listOf())
            if (SharedPreferenceModel.preferences?.contains(LAST_SELECTED_ITEM)
                    ?: error("Shared preference no init")
            ) {
                val lastSelectedItemId = SharedPreferenceModel.get<Int>(LAST_SELECTED_ITEM)
                val categoryModel = categoryList.first { it.id == lastSelectedItemId }
                onCategoryClick(categoryModel)
            }
        }
    }

    private fun initRVCategoryList() {
        categoryAdapter = CategoryAdapter()
        with(binding.rvMealCategory) {
            adapter = categoryAdapter
            categoryAdapter.setClickListener(this@MealListFragment)
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            setHasFixedSize(true)
        }
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

    override fun onCategoryClick(category: CategoryUI) {
        category.isSelected = category.isSelected.not()
        SharedPreferenceModel.put(category.id, LAST_SELECTED_ITEM)
        categories?.forEach { if (it != category) it.isSelected = false }
        categoryAdapter.updateCategoryList(categories ?: listOf())
        viewModel.startMealList(categoryName = category.categoryName)
        viewModel.mealList.observe(viewLifecycleOwner) { mealList ->
            mealAdapter.updateList(mealList)
        }
    }

    override fun onItemClick(meal: MealUI) {
        openFragment(meal.id)
    }

    private fun openFragment(mealId: Int) {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_main_container, MealDetailsFragment.newInstance(mealId))
            .addToBackStack(null)
            .commit()
    }

    companion object {
        private const val LAST_SELECTED_ITEM = "last_selected_item"
        fun newInstance(): MealListFragment = MealListFragment()
    }
}