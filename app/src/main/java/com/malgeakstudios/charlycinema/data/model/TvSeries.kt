package com.malgeakstudios.charlycinema.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.malgeakstudios.charlycinema.utils.APIUtils

@Entity(tableName = "tvserie")
data class TvSeries(
    @SerializedName("backdrop_path")
    val backdropPath : String?,
    @SerializedName("first_air_date")
    val firstAirDate : String,
    @PrimaryKey
    val id : Int,
    @SerializedName("in_production")
    val inProduction : Boolean,
    @SerializedName("last_air_date")
    val lastAirDate : String,
    val name : String,
    @SerializedName("number_of_espisodes")
    val numberOfEpisodes : Int,
    @SerializedName("number_of_seasons")
    val numberOfSeasons : Int,
    val overview : String?,
    val popularity : Double,
    @SerializedName("poster_path")
    val posterPath : String?,
    val status : String,
    val tagline : String?,
    val type : String,
    @SerializedName("vote_average")
    val voteAverage : Double,
    @SerializedName("vote_count")
    val voteCount : Double
){
    fun getImageCaratulaUrl() ="${APIUtils.BASE_IMAGE_URL}$posterPath"

    fun getImageBackUrl() = "${APIUtils.BASE_IMAGE_URL}$backdropPath"
}
