package com.malgeakstudios.charlycinema.utils.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.malgeakstudios.charlycinema.data.model.Genre

open class ObjectListConverter {
    @TypeConverter
    fun fromString(mString: String): List<Genre>? {
        val listType = object : TypeToken<List<Genre>>() {}.type
        return Gson().fromJson<List<Genre>>(mString, listType)
    }

    @TypeConverter
    fun fromList(mArray: List<Genre>?): String {
        val gson = Gson()
        return gson.toJson(mArray)
    }
}