package com.nikita.kut.android.epam_internship_android_kutsyy.app.repository

import com.nikita.kut.android.epam_internship_android_kutsyy.app.data.network.RetrofitClient
import com.nikita.kut.android.epam_internship_android_kutsyy.app.data.model.category.RemoteCategoryList
import com.nikita.kut.android.epam_internship_android_kutsyy.app.data.model.meal.RemoteMealList
import com.nikita.kut.android.epam_internship_android_kutsyy.app.data.model.mealdetails.RemoteMealDetailsList
import com.nikita.kut.android.epam_internship_android_kutsyy.app.util.exception.IncorrectStatusCodeException
import com.nikita.kut.android.epam_internship_android_kutsyy.app.util.toListMealUIModel
import com.nikita.kut.android.epam_internship_android_kutsyy.app.util.toMealDetailsUIModel
import com.nikita.kut.android.epam_internship_android_kutsyy.feature.mealdetails.model.MealDetailsUIModel
import com.nikita.kut.android.epam_internship_android_kutsyy.feature.meallist.model.MealUIModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException

class MealRepository {

    fun fetchMealList(
        categoryName: String,
        onComplete: (List<MealUIModel>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        RetrofitClient.retrofitService.getMeals(categoryName)
            .map { remoteMealList ->
                remoteMealList.toListMealUIModel()
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { listMeals ->
                    onComplete(listMeals)
                },
                { throwable ->
                    onError(throwable)
                }
            )
    }

    fun fetchMealDetails(
        mealId: Int,
        onComplete: (MealDetailsUIModel) -> Unit,
        onError: (Throwable) -> Unit
    ) {

        RetrofitClient.retrofitService.getMealDetails(mealId)
            .map { remoteMealDetailsList ->
                remoteMealDetailsList.mealDetails.first().toMealDetailsUIModel()
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { mealDetailsUiModel ->
                    onComplete(mealDetailsUiModel)
                },
                { throwable ->
                    onError(throwable)
                }
            )
    }
}