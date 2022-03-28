package com.malgeakstudios.charlycinema.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "season")
data class Season(
    @PrimaryKey
    val id : Int,
    @SerializedName("air_date")
    val airDate : String,
    @SerializedName("episode_count")
    val episodeCount : Int,
    val name : String,
    val overview : String,
    @SerializedName("poster_path")
    val posterPath : String?,
    @SerializedName("season_number")
    val seasonNumber : Int
)
