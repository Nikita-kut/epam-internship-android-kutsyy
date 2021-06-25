package com.nikita.kut.android.epam_internship_android_kutsyy.feature.mealdetails.presentation

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import com.nikita.kut.android.epam_internship_android_kutsyy.app.util.ViewBindingFragment
import com.nikita.kut.android.epam_internship_android_kutsyy.databinding.FragmentMealDetailsBinding
import com.nikita.kut.android.epam_internship_android_kutsyy.feature.meallist.model.Meal

class MealDetailsFragment :
    ViewBindingFragment<FragmentMealDetailsBinding>(FragmentMealDetailsBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        binding.toolbarMealDetails.setNavigationOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    private fun initViews() {
        val meal: Meal = requireArguments().getParcelable(KEY_MEAL)
            ?: error("No args meal from list fragment")
        binding.tvMealCategory.text = meal.category
        binding.tvMealName.text = meal.name
        binding.tvMealTag.text = meal.tag
    }

    companion object {
        private const val KEY_MEAL = "KEY_MEAL"
        fun newInstance(meal: Meal): MealDetailsFragment = MealDetailsFragment().apply {
            arguments = bundleOf(KEY_MEAL to meal)
        }
    }
}