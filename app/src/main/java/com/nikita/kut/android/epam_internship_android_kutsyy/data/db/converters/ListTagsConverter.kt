package com.nikita.kut.android.epam_internship_android_kutsyy.data.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListTagsConverter {

    @TypeConverter
    fun stringToList(flatStringList: String): List<String> =
        Gson().fromJson(flatStringList, object : TypeToken<List<String?>?>() {}.type)

    @TypeConverter
    fun listToString(listOfString: List<String>): String = Gson().toJson(listOfString)
}