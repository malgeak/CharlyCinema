package com.malgeakstudios.charlycinema.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "videos")
data class Videos(
    @SerializedName("iso_639_1")
    val iso_639 : String,
    @SerializedName("iso_3166_1")
    val iso_3166 : String,
    val name : String,
    val key : String,
    val site : String,
    val size : Int,
    val type : String,
    val official : Boolean,
    @PrimaryKey
    val id : String,
    var id_movie : Int
)
