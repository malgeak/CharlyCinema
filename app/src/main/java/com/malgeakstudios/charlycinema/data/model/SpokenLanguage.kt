package com.malgeakstudios.charlycinema.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "languages")
data class SpokenLanguage(
    @PrimaryKey
    @SerializedName("iso_639_1")
    val iso : String,
    val name : String
)
