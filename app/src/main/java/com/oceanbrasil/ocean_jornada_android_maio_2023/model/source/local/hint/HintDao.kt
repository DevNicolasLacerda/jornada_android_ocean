package com.oceanbrasil.ocean_jornada_android_maio_2023.model.source.local.hint

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HintDao {
    @Insert
    fun insert(hint: HintEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(hints: List<HintEntity>)

    @Query("SELECT * FROM hints")
    fun findAll(): List<HintEntity>
}
