package com.nikita.kut.android.epam_internship_android_kutsyy.di

import android.content.Context
import androidx.room.Room
import com.nikita.kut.android.epam_internship_android_kutsyy.data.db.AppDataBase
import com.nikita.kut.android.epam_internship_android_kutsyy.data.network.MealsApi
import com.nikita.kut.android.epam_internship_android_kutsyy.data.network.RetrofitClient
import com.nikita.kut.android.epam_internship_android_kutsyy.data.repository.CategoryRepositoryImpl
import com.nikita.kut.android.epam_internship_android_kutsyy.data.repository.MealRepositoryImpl
import com.nikita.kut.android.epam_internship_android_kutsyy.domain.repository.CategoryRepository
import com.nikita.kut.android.epam_internship_android_kutsyy.domain.repository.MealRepository
import com.nikita.kut.android.epam_internship_android_kutsyy.domain.usecase.FetchCategoryListUseCase
import com.nikita.kut.android.epam_internship_android_kutsyy.domain.usecase.FetchMealDetailsUseCase
import com.nikita.kut.android.epam_internship_android_kutsyy.domain.usecase.FetchMealListUseCase
import dagger.Module
import dagger.Provides
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule() {

    @Provides
    @Singleton
    fun provideDataBase(context: Context): AppDataBase =
        Room.databaseBuilder(context, AppDataBase::class.java, AppDataBase.DB_NAME).build()

    @Provides
    @Singleton
    fun provideMealsApi(): MealsApi =
        Retrofit.Builder().baseUrl(RetrofitClient.BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MealsApi::class.java)

    @Provides
    fun provideFetchCategoryListUseCase(repository: CategoryRepository): FetchCategoryListUseCase =
        FetchCategoryListUseCase(repository)

    @Provides
    fun provideFetchMealDetailsUseCase(repository: MealRepository): FetchMealDetailsUseCase =
        FetchMealDetailsUseCase(repository)

    @Provides
    fun provideFetchMealListUseCase(repository: MealRepository): FetchMealListUseCase =
        FetchMealListUseCase(repository)

    @Provides
    fun provideCategoryRepository(mealsApi: MealsApi, dataBase: AppDataBase): CategoryRepository =
        CategoryRepositoryImpl(mealsApi, dataBase)

    @Provides
    fun provideMealRepository(mealsApi: MealsApi, dataBase: AppDataBase): MealRepository =
        MealRepositoryImpl(mealsApi, dataBase)

}