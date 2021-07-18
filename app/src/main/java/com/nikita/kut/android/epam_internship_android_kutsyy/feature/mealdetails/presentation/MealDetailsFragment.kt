package com.nikita.kut.android.epam_internship_android_kutsyy.feature.mealdetails.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nikita.kut.android.epam_internship_android_kutsyy.R
import com.nikita.kut.android.epam_internship_android_kutsyy.app.repository.CategoryRepository
import com.nikita.kut.android.epam_internship_android_kutsyy.app.repository.MealRepository
import com.nikita.kut.android.epam_internship_android_kutsyy.app.util.AutoClearedValue
import com.nikita.kut.android.epam_internship_android_kutsyy.app.util.ViewBindingFragment
import com.nikita.kut.android.epam_internship_android_kutsyy.app.util.setImage
import com.nikita.kut.android.epam_internship_android_kutsyy.app.util.toMealDetailsUIModel
import com.nikita.kut.android.epam_internship_android_kutsyy.databinding.FragmentMealDetailsBinding
import com.nikita.kut.android.epam_internship_android_kutsyy.feature.mealdetails.model.MealDetailsUIModel
import com.nikita.kut.android.epam_internship_android_kutsyy.feature.mealdetails.presentation.adapter.TagAdapter
import io.reactivex.rxjava3.disposables.Disposable

class MealDetailsFragment :
    ViewBindingFragment<FragmentMealDetailsBinding>(FragmentMealDetailsBinding::inflate) {

    private val repository by lazy { MealRepository(requireContext()) }

    private var tagAdapter by AutoClearedValue<TagAdapter>()

    private var fetchMealDetailsDisposable: Disposable? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRVTagList()
        initViewsFromNetwork()
        binding.toolbarMealDetails.setNavigationOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fetchMealDetailsDisposable?.dispose()
    }

    private fun initViewsFromNetwork() {
        val mealId = requireArguments().getInt(KEY_MEAL_ID)
        fetchMealDetailsDisposable = repository.fetchMealDetails(
            mealId = mealId,
            onComplete = { mealDetailsUiModel ->
                initViews(mealDetailsUiModel)
            },
            onError = { t ->
                Log.e("Server", "enqueue request error = ${t.message}", t)
                toast(resources.getString(R.string.load_error))
            }
        )
    }

    private fun initViews(mealDetails: MealDetailsUIModel) {
        with(binding) {
            ivMealDetailsMainPic.setImage(mealDetails.mealPicture)
            tvMealCategory.text = mealDetails.mealArea
            tvMealName.text = mealDetails.mealName
            tagAdapter.updateTagList(mealDetails.mealTags)
            tvMealIngredients.text = mealDetails.mealIngredients
        }
    }

    private fun toast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
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