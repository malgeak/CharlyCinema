package com.malgeakstudios.charlycinema.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "moviesplayingnow")
data class MoviesPlayingNow(
    @PrimaryKey
    val id : Int,
    val page : Int

)
