package com.nikita.kut.android.epam_internship_android_kutsyy.app.data.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class ListTagsConverter {

    @TypeConverter
    fun stringToList(flatStringList: String): List<String> {
        val listType: Type = object : TypeToken<List<String?>?>() {}.type
        return Gson().fromJson(flatStringList, listType)

    }

    @TypeConverter
    fun listToString(listOfString: List<String>): String {
        return Gson().toJson(listOfString)
    }

}