package com.nikita.kut.android.epam_internship_android_kutsyy.presentation.feature.main.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nikita.kut.android.epam_internship_android_kutsyy.data.db.AppDataBase
import com.nikita.kut.android.epam_internship_android_kutsyy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppDataBase.initDatabase(this)
    }

    override fun onBackPressed() {
        val mainFragmentFragmentManager = supportFragmentManager
            .findFragmentByTag(MainFragment.MAIN_FRAGMENT_TAG)
            ?.childFragmentManager
        val backStackCount =
            mainFragmentFragmentManager
                ?.backStackEntryCount

        if (backStackCount == null) {
            super.onBackPressed()
        } else {
            if (backStackCount > 0) {
                mainFragmentFragmentManager.popBackStack()
            } else {
                super.onBackPressed()
            }
        }
    }
}