package com.nikita.kut.android.epam_internship_android_kutsyy.di

import android.content.Context
import com.nikita.kut.android.epam_internship_android_kutsyy.data.db.AppDataBase
import com.nikita.kut.android.epam_internship_android_kutsyy.presentation.feature.meallist.view.MealListFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class],
//    dependencies = [Context::class]
)
@Singleton
interface AppComponent {

    val database: AppDataBase

//    fun inject(mealListFragment: MealListFragment)
}