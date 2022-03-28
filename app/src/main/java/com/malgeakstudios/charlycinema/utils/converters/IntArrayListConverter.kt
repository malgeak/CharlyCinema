package com.aherrera.tmdb.utils.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

open class IntArrayListConverter {
    @TypeConverter
    fun fromString(mString: String): List<Int>? {
        val listType = object : TypeToken<List<Int>>() {}.type
        return Gson().fromJson<List<Int>>(mString, listType)
    }

    @TypeConverter
    fun fromList(mArray: List<Int>): String {
        val gson = Gson()
        return gson.toJson(mArray)
    }
}