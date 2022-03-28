package com.malgeakstudios.charlycinema.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "productioncompany")
data class ProductionCompany(
    @PrimaryKey
    val id : Int,
    val name : String,
    @SerializedName("logo_path")
    val logoPath : String?
)
