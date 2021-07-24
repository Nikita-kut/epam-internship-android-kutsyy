package com.nikita.kut.android.epam_internship_android_kutsyy.presentation.feature.mealdetails.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nikita.kut.android.epam_internship_android_kutsyy.domain.usecase.FetchMealDetailsUseCase
import com.nikita.kut.android.epam_internship_android_kutsyy.presentation.model.MealDetailsUI
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MealDetailsViewModel(private val fetchMealDetailsUseCase: FetchMealDetailsUseCase) :
    ViewModel() {

    private val mutableMealDetails: MutableLiveData<MealDetailsUI> = MutableLiveData()
    val mealDetails: LiveData<MealDetailsUI>
        get() = mutableMealDetails

    private var fetchMealDetailsDisposable: Disposable? = null

    fun startMealDetails(mealId: Int) {
        fetchMealDetailsDisposable = fetchMealDetailsUseCase.fetchMealDetails(mealId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { mealDetails ->
                    mutableMealDetails.value = mealDetails
                },
                { error ->
                    Log.e("Server", "enqueue request error = ${error.message}", error)
                }
            )
    }

    override fun onCleared() {
        fetchMealDetailsDisposable?.dispose()
        super.onCleared()
    }

}