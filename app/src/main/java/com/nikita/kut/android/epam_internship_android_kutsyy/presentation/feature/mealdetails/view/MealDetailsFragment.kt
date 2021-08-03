package com.nikita.kut.android.epam_internship_android_kutsyy.presentation.feature.mealdetails.view

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nikita.kut.android.epam_internship_android_kutsyy.App
import com.nikita.kut.android.epam_internship_android_kutsyy.data.db.AppDataBase
import com.nikita.kut.android.epam_internship_android_kutsyy.data.network.RetrofitClient
import com.nikita.kut.android.epam_internship_android_kutsyy.data.repository.MealRepositoryImpl
import com.nikita.kut.android.epam_internship_android_kutsyy.databinding.FragmentMealDetailsBinding
import com.nikita.kut.android.epam_internship_android_kutsyy.domain.usecase.FetchMealDetailsUseCase
import com.nikita.kut.android.epam_internship_android_kutsyy.presentation.feature.mealdetails.view.adapter.TagAdapter
import com.nikita.kut.android.epam_internship_android_kutsyy.presentation.feature.mealdetails.viewmodel.MealDetailsViewModel
import com.nikita.kut.android.epam_internship_android_kutsyy.presentation.feature.mealdetails.viewmodel.MealDetailsViewModelFactory
import com.nikita.kut.android.epam_internship_android_kutsyy.presentation.model.MealDetailsUI
import com.nikita.kut.android.epam_internship_android_kutsyy.util.memory.AutoClearedValue
import com.nikita.kut.android.epam_internship_android_kutsyy.util.memory.ViewBindingFragment
import com.nikita.kut.android.epam_internship_android_kutsyy.util.setImage

class MealDetailsFragment :
    ViewBindingFragment<FragmentMealDetailsBinding>(FragmentMealDetailsBinding::inflate) {

    private var tagAdapter by AutoClearedValue<TagAdapter>()

    private val viewModel: MealDetailsViewModel by viewModels {
        MealDetailsViewModelFactory(
            FetchMealDetailsUseCase(
                MealRepositoryImpl(
                    RetrofitClient.mealsApi,
//                    AppDataBase.getInstance()
                    App.appComponent.database
                )
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRVTagList()
        initViewModel()
        binding.toolbarMealDetails.setNavigationOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    private fun initViewModel() {
        val mealId = requireArguments().getInt(KEY_MEAL_ID)
        viewModel.startMealDetails(mealId)
        viewModel.mealDetails.observe(viewLifecycleOwner) { mealDetails -> initViews(mealDetails) }
    }

    private fun initViews(mealDetails: MealDetailsUI) {
        with(binding) {
            ivMealDetailsMainPic.setImage(mealDetails.mealPicture)
            tvMealCategory.text = mealDetails.mealArea
            tvMealName.text = mealDetails.mealName
            tagAdapter.updateTagList(mealDetails.mealTags)
            tvMealIngredients.text = mealDetails.mealIngredients
        }
    }

    private fun initRVTagList() {
        tagAdapter = TagAdapter()
        with(binding.rvTagList) {
            adapter = tagAdapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            setHasFixedSize(true)
        }
    }

    companion object {
        private const val KEY_MEAL_ID = "KEY_MEAL_ID"
        fun newInstance(mealId: Int): MealDetailsFragment =
            MealDetailsFragment().apply {
                arguments = bundleOf(KEY_MEAL_ID to mealId)
            }
    }
}