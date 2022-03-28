package com.malgeakstudios.charlycinema.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.malgeakstudios.charlycinema.utils.APIUtils

@Entity(tableName = "movie")
data class Movie(
    val adult : Boolean,
    @SerializedName("backdrop_path")
    val backdropPath : String?,
    val genres : List<Genre?>?,
    @PrimaryKey
    val id : Int,
    @SerializedName("original_language")
    val originalLanguage : String?,
    @SerializedName("original_title")
    val originalTitle : String?,
    val overview : String?,
    val popularity : Double?,
    @SerializedName("poster_path")
    val posterPath : String?,
    @SerializedName("production_companies")
    val productionCompanies : List<ProductionCompany>?,
    val status : String?,
    @SerializedName("spoken_languages")
    val spokenLanguages : List<SpokenLanguage>?,
    val tagline : String?,
    val title : String?,
    val video : Boolean?,
    @SerializedName("vote_average")
    val voteAverage : Double?,
    @SerializedName("vote_count")
    val voteCount : Double?
){
    fun getImageCaratulaUrl() ="${APIUtils.BASE_IMAGE_URL}$posterPath"

    fun getImageBackUrl() = "${APIUtils.BASE_IMAGE_URL}$backdropPath"
}
