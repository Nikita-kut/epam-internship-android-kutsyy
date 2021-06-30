package com.nikita.kut.android.epam_internship_android_kutsyy.feature.mealdetails.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import com.nikita.kut.android.epam_internship_android_kutsyy.R
import com.nikita.kut.android.epam_internship_android_kutsyy.app.repository.MealDetailsRepository
import com.nikita.kut.android.epam_internship_android_kutsyy.app.util.ViewBindingFragment
import com.nikita.kut.android.epam_internship_android_kutsyy.app.util.getMealDetailsUIModel
import com.nikita.kut.android.epam_internship_android_kutsyy.app.util.setImageMealDetails
import com.nikita.kut.android.epam_internship_android_kutsyy.databinding.FragmentMealDetailsBinding
import com.nikita.kut.android.epam_internship_android_kutsyy.feature.mealdetails.model.RemoteMealDetails

class MealDetailsFragment :
    ViewBindingFragment<FragmentMealDetailsBinding>(FragmentMealDetailsBinding::inflate) {

    private val repository = MealDetailsRepository()

    private lateinit var remoteMealDetails: RemoteMealDetails

    private val mealId: Int by lazy { requireArguments().getInt(KEY_MEAL_ID) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewsFromNetwork()
        binding.toolbarMealDetails.setNavigationOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    private fun initViewsFromNetwork() {
        repository.initMealDetailsFromNetwork(
            mealId = mealId,
            onComplete = { remoteMealDetails ->
                this.remoteMealDetails = remoteMealDetails
                initViews()
            },
            onError = { t ->
                Log.e("Server", "enqueue request error = ${t.message}", t)
                toast(resources.getString(R.string.load_error))
            }
        )
    }

    private fun initViews() {
        val mealDetails = remoteMealDetails.mealDetails.first()
        val mealDetailsUIModel = mealDetails.getMealDetailsUIModel()
        with(binding) {
            ivMealDetailsMainPic.setImageMealDetails(mealDetailsUIModel.mealPicture)
            tvMealCategory.text = mealDetailsUIModel.mealArea
            tvMealName.text = mealDetailsUIModel.mealName
            tvMealTag.text = mealDetailsUIModel.mealTag
            tvMealIngredients.text = mealDetailsUIModel.mealIngredients
        }
    }

    private fun toast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val KEY_MEAL_ID = "KEY_MEAL_ID"
        fun newInstance(mealId: Int): MealDetailsFragment =
            MealDetailsFragment().apply {
                arguments = bundleOf(KEY_MEAL_ID to mealId)
            }
    }
}