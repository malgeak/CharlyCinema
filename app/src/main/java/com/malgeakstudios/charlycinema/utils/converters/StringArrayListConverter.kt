package com.aherrera.tmdb.utils.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

open class StringArrayListConverter {
    @TypeConverter
    fun fromString(mString: String): List<String>? {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson<List<String>>(mString, listType)
    }

    @TypeConverter
    fun fromList(mArray: List<String>?): String {
        val gson = Gson()
        return gson.toJson(mArray)
    }
}
