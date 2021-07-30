package com.nikita.kut.android.epam_internship_android_kutsyy

import com.nikita.kut.android.epam_internship_android_kutsyy.data.model.network.category.CategoryListRemote
import com.nikita.kut.android.epam_internship_android_kutsyy.data.model.network.category.CategoryRemote
import com.nikita.kut.android.epam_internship_android_kutsyy.data.network.RetrofitClient
import org.junit.Assert
import org.junit.Test

class NetworkRequestTest {

    @Test
    fun is_category_list_get() {
        var networkData = CategoryListRemote(listOf())
        RetrofitClient.mealsApi.getCategories().subscribe({
            networkData = it
        }, {})
        val categoryListRemote = CategoryListRemote(
            categories = listOf(
                CategoryRemote(
                    id = 1,
                    categoryName = "Beef",
                    categoryPicture = "https://www.themealdb.com/images/category/beef.png"
                ),
                CategoryRemote(
                    id = 2,
                    categoryName = "Chicken",
                    categoryPicture = "https://www.themealdb.com/images/category/chicken.png"
                ), CategoryRemote(
                    id = 3,
                    categoryName = "Dessert",
                    categoryPicture = "https://www.themealdb.com/images/category/dessert.png"
                ), CategoryRemote(
                    id = 4,
                    categoryName = "Lamb",
                    categoryPicture = "https://www.themealdb.com/images/category/lamb.png"
                ), CategoryRemote(
                    id = 5,
                    categoryName = "Miscellaneous",
                    categoryPicture = "https://www.themealdb.com/images/category/miscellaneous.png"
                ), CategoryRemote(
                    id = 6,
                    categoryName = "Pasta",
                    categoryPicture = "https://www.themealdb.com/images/category/pasta.png"
                ), CategoryRemote(
                    id = 7,
                    categoryName = "Pork",
                    categoryPicture = "https://www.themealdb.com/images/category/pork.png"
                ), CategoryRemote(
                    id = 8,
                    categoryName = "Seafood",
                    categoryPicture = "https://www.themealdb.com/images/category/seafood.png"
                ), CategoryRemote(
                    id = 9,
                    categoryName = "Side",
                    categoryPicture = "https://www.themealdb.com/images/category/side.png"
                ), CategoryRemote(
                    id = 10,
                    categoryName = "Starter",
                    categoryPicture = "https://www.themealdb.com/images/category/starter.png"
                ), CategoryRemote(
                    id = 11,
                    categoryName = "Vegan",
                    categoryPicture = "https://www.themealdb.com/images/category/vegan.png"
                ), CategoryRemote(
                    id = 12,
                    categoryName = "Vegetarian",
                    categoryPicture = "https://www.themealdb.com/images/category/vegetarian.png"
                ), CategoryRemote(
                    id = 13,
                    categoryName = "Breakfast",
                    categoryPicture = "https://www.themealdb.com/images/category/breakfast.png"
                ), CategoryRemote(
                    id = 14,
                    categoryName = "Goat",
                    categoryPicture = "https://www.themealdb.com/images/category/goat.png"
                )
            )
        )
        Assert.assertEquals(
            networkData, categoryListRemote
        )
    }
}