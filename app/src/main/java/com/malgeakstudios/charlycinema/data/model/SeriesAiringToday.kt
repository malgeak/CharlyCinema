package com.malgeakstudios.charlycinema.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "seriesairingtoday")
data class SeriesAiringToday(
    @PrimaryKey
    val id : Int,
    val page : Int

)
