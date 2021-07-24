package com.nikita.kut.android.epam_internship_android_kutsyy.presentation.feature.meallist.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nikita.kut.android.epam_internship_android_kutsyy.domain.usecase.FetchCategoryListUseCase
import com.nikita.kut.android.epam_internship_android_kutsyy.domain.usecase.FetchMealListUseCase
import com.nikita.kut.android.epam_internship_android_kutsyy.presentation.model.CategoryUI
import com.nikita.kut.android.epam_internship_android_kutsyy.presentation.model.MealUI
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MealListViewModel(
    private val fetchCategoryListUseCase: FetchCategoryListUseCase,
    private val fetchMealListUseCase: FetchMealListUseCase
) : ViewModel() {

    private val mutableCategoryList: MutableLiveData<List<CategoryUI>> = MutableLiveData()
    val categoryList: LiveData<List<CategoryUI>>
        get() = mutableCategoryList

    private val mutableMealList: MutableLiveData<List<MealUI>> = MutableLiveData()
    val mealList: LiveData<List<MealUI>>
        get() = mutableMealList

    private var fetchCategoryDisposable: Disposable? = null

    private var fetchMealListDisposable: Disposable? = null

    fun startCategoryList() {
        fetchCategoryDisposable = fetchCategoryListUseCase.fetchCategoryList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { categoryList ->
                    mutableCategoryList.value = categoryList
                },
                { error ->
                    Log.e("Server", "enqueue request error = ${error.message}", error)
                }
            )
    }

    fun startMealList(categoryName: String) {
        fetchMealListDisposable = fetchMealListUseCase.fetchMealList(categoryName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { mealList ->
                    mutableMealList.value = mealList
                },
                { error ->
                    Log.e("Server", "enqueue request error = ${error.message}", error)
                }
            )
    }

    override fun onCleared() {
        fetchCategoryDisposable?.dispose()
        fetchMealListDisposable?.dispose()
        super.onCleared()
    }
}