package com.nikita.kut.android.epam_internship_android_kutsyy.di

import android.content.Context
import com.nikita.kut.android.epam_internship_android_kutsyy.domain.usecase.FetchCategoryListUseCase
import com.nikita.kut.android.epam_internship_android_kutsyy.domain.usecase.FetchMealDetailsUseCase
import com.nikita.kut.android.epam_internship_android_kutsyy.domain.usecase.FetchMealListUseCase
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {

    val fetchCategoryListUseCase: FetchCategoryListUseCase

    val fetchMealListUseCase: FetchMealListUseCase

    val fetchMealDetailsUseCase: FetchMealDetailsUseCase

    @Component.Builder
    abstract class Builder {

        @BindsInstance
        abstract fun bindContext(context: Context): Builder
        abstract fun build(): AppComponent
    }

}