package com.nikita.kut.android.epam_internship_android_kutsyy.app.repository

import com.nikita.kut.android.epam_internship_android_kutsyy.app.data.model.category.RemoteCategoryList
import com.nikita.kut.android.epam_internship_android_kutsyy.app.data.network.RetrofitClient
import com.nikita.kut.android.epam_internship_android_kutsyy.app.data.model.mealdetails.RemoteMealDetailsList
import com.nikita.kut.android.epam_internship_android_kutsyy.app.util.exception.IncorrectStatusCodeException
import com.nikita.kut.android.epam_internship_android_kutsyy.app.util.toListCategoryUIModel
import com.nikita.kut.android.epam_internship_android_kutsyy.feature.meallist.model.CategoryUIModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException

class CategoryRepository {

    fun fetchCategoryList(
        onComplete: (List<CategoryUIModel>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        RetrofitClient.retrofitService.getCategories()
            .map { remoteCategoryList ->
                remoteCategoryList.toListCategoryUIModel()
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { categoryList ->
                    onComplete(categoryList)
                },
                { throwable ->
                    onError(throwable)
                })
    }
}