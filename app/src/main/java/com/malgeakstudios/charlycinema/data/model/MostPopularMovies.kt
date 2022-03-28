package com.malgeakstudios.charlycinema.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mostpopularmovies")
data class MostPopularMovies(
    @PrimaryKey
    val id : Int,
    val page : Int
)
