package com.malgeakstudios.charlycinema.utils.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.malgeakstudios.charlycinema.data.model.Creator
import com.malgeakstudios.charlycinema.data.model.Genre

open class CreatorsListConverter {
    @TypeConverter
    fun fromString(mString: String): List<Creator>? {
        val listType = object : TypeToken<List<Creator>>() {}.type
        return Gson().fromJson<List<Creator>>(mString, listType)
    }

    @TypeConverter
    fun fromList(mArray: List<Creator>): String {
        val gson = Gson()
        return gson.toJson(mArray)
    }
}