package com.nikita.kut.android.epam_internship_android_kutsyy

import android.app.Application
import com.nikita.kut.android.epam_internship_android_kutsyy.di.AppComponent
import com.nikita.kut.android.epam_internship_android_kutsyy.di.DaggerAppComponent

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().bindContext(applicationContext).build()
    }

    companion object {
        lateinit var appComponent: AppComponent
    }
}