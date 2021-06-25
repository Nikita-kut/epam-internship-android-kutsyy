package com.nikita.kut.android.epam_internship_android_kutsyy.feature.main.presentation

import android.os.Bundle
import android.view.View
import com.nikita.kut.android.epam_internship_android_kutsyy.app.util.ViewBindingFragment
import com.nikita.kut.android.epam_internship_android_kutsyy.databinding.FragmentMainBinding
import com.nikita.kut.android.epam_internship_android_kutsyy.feature.meallist.presentation.MealListFragment

class MainFragment : ViewBindingFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        openFragment()
    }

    private fun openFragment() {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(binding.fragmentMainContainer.id, MealListFragment.newInstance())
            .commit()
    }

    companion object {
        const val MAIN_FRAGMENT_TAG = "MAIN_FRAGMENT_TAG"

        fun newInstance(): MainFragment = MainFragment()
    }
}