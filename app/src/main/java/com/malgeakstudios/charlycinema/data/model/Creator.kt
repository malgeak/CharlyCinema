package com.malgeakstudios.charlycinema.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "creator")
data class Creator(
    @PrimaryKey
    val id : Int,
    val name : String
)
