package com.malgeakstudios.charlycinema.utils.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.malgeakstudios.charlycinema.data.model.Genre
import com.malgeakstudios.charlycinema.data.model.ProductionCompany

open class ProductCompaniesListConverter {
    @TypeConverter
    fun fromString(mString: String): List<ProductionCompany>? {
        val listType = object : TypeToken<List<ProductionCompany>>() {}.type
        return Gson().fromJson<List<ProductionCompany>>(mString, listType)
    }

    @TypeConverter
    fun fromList(mArray: List<ProductionCompany>?): String {
        val gson = Gson()
        return gson.toJson(mArray)
    }
}