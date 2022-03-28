package com.malgeakstudios.charlycinema.utils.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.malgeakstudios.charlycinema.data.model.Season

open class SeasonListConverter {
    @TypeConverter
    fun fromString(mString: String): List<Season>? {
        val listType = object : TypeToken<List<Season>>() {}.type
        return Gson().fromJson<List<Season>>(mString, listType)
    }

    @TypeConverter
    fun fromList(mArray: List<Season>): String {
        val gson = Gson()
        return gson.toJson(mArray)
    }
}