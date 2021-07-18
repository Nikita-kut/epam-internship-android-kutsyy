package com.nikita.kut.android.epam_internship_android_kutsyy.feature.meallist.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nikita.kut.android.epam_internship_android_kutsyy.R
import com.nikita.kut.android.epam_internship_android_kutsyy.app.data.preference.SharedPreferenceModel
import com.nikita.kut.android.epam_internship_android_kutsyy.app.repository.CategoryRepository
import com.nikita.kut.android.epam_internship_android_kutsyy.app.repository.MealRepository
import com.nikita.kut.android.epam_internship_android_kutsyy.app.util.AutoClearedValue
import com.nikita.kut.android.epam_internship_android_kutsyy.app.util.ViewBindingFragment
import com.nikita.kut.android.epam_internship_android_kutsyy.databinding.FragmentMealListBinding
import com.nikita.kut.android.epam_internship_android_kutsyy.feature.mealdetails.presentation.MealDetailsFragment
import com.nikita.kut.android.epam_internship_android_kutsyy.feature.meallist.model.CategoryUIModel
import com.nikita.kut.android.epam_internship_android_kutsyy.feature.meallist.model.MealUIModel
import com.nikita.kut.android.epam_internship_android_kutsyy.feature.meallist.presentation.adapter.category.CategoryAdapter
import com.nikita.kut.android.epam_internship_android_kutsyy.feature.meallist.presentation.adapter.meal.MealAdapter
import io.reactivex.rxjava3.disposables.Disposable

class MealListFragment :
    ViewBindingFragment<FragmentMealListBinding>(FragmentMealListBinding::inflate),
    MealAdapter.OnMealItemClickListener,
    CategoryAdapter.OnCategoryItemClickListener {

    private val mealRepository by lazy { MealRepository(requireContext()) }

    private val categoryRepository by lazy { CategoryRepository(requireContext()) }

    private var mealAdapter by AutoClearedValue<MealAdapter>()

    private var categoryAdapter by AutoClearedValue<CategoryAdapter>()

    private var categories: List<CategoryUIModel>? = null

    private var fetchCategoryDisposable: Disposable? = null

    private var fetchMealListDisposable: Disposable? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        SharedPreferenceModel.with(requireActivity().application)
        initRVCategoryList()
        initCategoriesList()
        initRVMealList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fetchCategoryDisposable?.dispose()
        fetchMealListDisposable?.dispose()
    }

    private fun initCategoriesList() {
        fetchCategoryDisposable = categoryRepository.fetchCategoryList(
            onComplete = { categoryList ->
                this.categories = categoryList
                categoryAdapter.updateCategoryList(categories ?: listOf())
                if (SharedPreferenceModel.preferences?.contains(LAST_SELECTED_ITEM)
                        ?: error("Shared preference no init")
                ) {
                    val lastSelectedItemId = SharedPreferenceModel.get<Int>(LAST_SELECTED_ITEM)
                    val categoryModel = categoryList.first { it.id == lastSelectedItemId }
                    onCategoryClick(categoryModel)
                }
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

    override fun onCategoryClick(category: CategoryUIModel) {
        category.isSelected = category.isSelected.not()
        SharedPreferenceModel.put(category.id, LAST_SELECTED_ITEM)
        categories?.forEach { if (it != category) it.isSelected = false }
        categoryAdapter.updateCategoryList(categories ?: listOf())
        fetchMealListDisposable = mealRepository.fetchMealList(
            categoryName = category.categoryName,
            onComplete = { mealList ->
                mealAdapter.updateList(mealList)
            },
            onError = { t ->
                Log.e("Server", "enqueue request error = ${t.message}", t)
                toast(resources.getString(R.string.load_error))
            }
        )
    }

    override fun onItemClick(meal: MealUIModel) {
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
        private const val LAST_SELECTED_ITEM = "last_selected_item"
        fun newInstance(): MealListFragment = MealListFragment()
    }
}