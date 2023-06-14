package com.oceanbrasil.ocean_jornada_android_maio_2023.model.source.local

import android.app.Application
import com.oceanbrasil.ocean_jornada_android_maio_2023.model.domain.Hint
import com.oceanbrasil.ocean_jornada_android_maio_2023.model.source.local.hint.HintEntity

class HintsLocalDataSource(application: Application) {
    private val appDatabase = AppDatabase.getInstance(application)
    private val hintDao = appDatabase.hintDao()

    fun insertAll(hintsDomain: List<Hint>) {
        val hintsEntities = hintsDomain.map {
            HintEntity(it.id, it.name, it.latitude, it.longitude)
        }

        hintDao.insertAll(hintsEntities)
    }

    fun findAll(): List<Hint> {
        val hintsEntities = hintDao.findAll()

        val hintsDomain = hintsEntities.map {
            Hint(it.id, it.name, it.latitude, it.longitude)
        }

        return hintsDomain
    }
}
