package com.oceanbrasil.ocean_jornada_android_maio_2023.model.source.local.hint

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hints")
data class HintEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val latitude: Double,
    val longitude: Double
)
