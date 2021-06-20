package com.nikita.kut.android.epam_internship_android_kutsyy.app.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nikita.kut.android.epam_internship_android_kutsyy.feature.main.presentation.MainFragment
import com.nikita.kut.android.epam_internship_android_kutsyy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) openFragment()
    }

    private fun openFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(
                binding.activityMainContainer.id,
                MainFragment(),
                MainFragment.MAIN_FRAGMENT_TAG
            )
            .commit()
    }

    override fun onBackPressed() {
        val mainFragmentFragmentManager = supportFragmentManager
            .findFragmentByTag(MainFragment.MAIN_FRAGMENT_TAG)
            ?.childFragmentManager
        val backStackCount =
            mainFragmentFragmentManager
                ?.backStackEntryCount
        if (backStackCount == 0) {
            super.onBackPressed()
        } else {
            mainFragmentFragmentManager?.popBackStack()
        }
    }
}