package com.nikita.kut.android.epam_internship_android_kutsyy.data.preference

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.GsonBuilder

object SharedPreferenceModel {
    var preferences: SharedPreferences? = null

    fun with(application: Application) {
        preferences = application.getSharedPreferences(PREFERENCES_FILE_NAME, Context.MODE_PRIVATE)
    }

    fun <T> put(myObject: T, key: String) {
        val jsonString = GsonBuilder().create().toJson(myObject)
        preferences?.edit()?.putString(key, jsonString)?.apply()
    }

    inline fun <reified T> get(key: String): T? {
        val value = preferences?.getString(key, null)
        return GsonBuilder().create().fromJson(value, T::class.java)
    }

    private const val PREFERENCES_FILE_NAME = "PREFERENCES_FILE_NAME"
}
