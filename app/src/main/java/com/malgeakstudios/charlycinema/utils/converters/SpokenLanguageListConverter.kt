package com.malgeakstudios.charlycinema.utils.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.malgeakstudios.charlycinema.data.model.Genre
import com.malgeakstudios.charlycinema.data.model.SpokenLanguage

open class SpokenLanguageListConverter {
    @TypeConverter
    fun fromString(mString: String): List<SpokenLanguage>? {
        val listType = object : TypeToken<List<SpokenLanguage>>() {}.type
        return Gson().fromJson<List<SpokenLanguage>>(mString, listType)
    }

    @TypeConverter
    fun fromList(mArray: List<SpokenLanguage>?): String {
        val gson = Gson()
        return gson.toJson(mArray)
    }
}