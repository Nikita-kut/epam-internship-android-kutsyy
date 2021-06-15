package com.nikita.kut.android.epam_internship_android_kutsyy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nikita.kut.android.epam_internship_android_kutsyy.databinding.FragmentMealDetailsBinding
import com.nikita.kut.android.epam_internship_android_kutsyy.model.Meal

class MealDetailsFragment : Fragment() {

    private lateinit var binding: FragmentMealDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMealDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        binding.toolbarMealList.setNavigationOnClickListener {
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
        fun newInstanceWithArgs(meal: Meal): MealDetailsFragment {
            val fragment = MealDetailsFragment()
            val args = Bundle().apply {
                putParcelable(KEY_MEAL, meal)
            }
            fragment.arguments = args
            return fragment
        }
    }
}