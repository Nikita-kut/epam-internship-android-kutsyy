package com.nikita.kut.android.epam_internship_android_kutsyy

import com.nikita.kut.android.epam_internship_android_kutsyy.domain.model.CategoryEntity
import com.nikita.kut.android.epam_internship_android_kutsyy.presentation.mapper.toListCategoryUI
import org.junit.Assert
import org.junit.Test

class MapperTest {

    @Test
    fun is_UI_model() {
        val listCategoryEntity = listOf(CategoryEntity(1, "Category name", "Category Picture"))
        val listModelUi = listCategoryEntity.toListCategoryUI()
        Assert.assertEquals(listCategoryEntity, listModelUi)
    }
}