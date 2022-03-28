package com.malgeakstudios.charlycinema.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mostpopularseries")
data class MostPopularSeries(
    @PrimaryKey
    val id : Int,
    val page : Int

)
