package com.nikita.kut.android.epam_internship_android_kutsyy

import android.os.Build
import com.nikita.kut.android.epam_internship_android_kutsyy.data.db.AppDataBase
import com.nikita.kut.android.epam_internship_android_kutsyy.data.network.RetrofitClient
import com.nikita.kut.android.epam_internship_android_kutsyy.data.repository.CategoryRepositoryImpl
import com.nikita.kut.android.epam_internship_android_kutsyy.domain.usecase.FetchCategoryListUseCase
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class FetchCategoryListUseCaseTest {

    private val categoryRepository = CategoryRepositoryImpl(RetrofitClient.mealsApi)
    private val fetchCategoryListUseCase = FetchCategoryListUseCase(categoryRepository)

    @Before
    fun init() {
        AppDataBase.initDatabase(RuntimeEnvironment.application.applicationContext)
    }

    @Test
    fun is_get_List_Category() {
        val listCategory = fetchCategoryListUseCase.fetchCategoryList()
        Assert.assertNotNull(listCategory)
    }
}