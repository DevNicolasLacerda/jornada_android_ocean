package com.oceanbrasil.ocean_jornada_android_maio_2023.model.repository

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.oceanbrasil.ocean_jornada_android_maio_2023.model.domain.Hint
import com.oceanbrasil.ocean_jornada_android_maio_2023.model.source.local.HintsLocalDataSource
import com.oceanbrasil.ocean_jornada_android_maio_2023.model.source.remote.HintCallback
import com.oceanbrasil.ocean_jornada_android_maio_2023.model.source.remote.HintsRemoteDataSource

class HintsRepository(application: Application) {
    private val hintsRemoteDataSource = HintsRemoteDataSource

    private val hintsLocalDataSource = HintsLocalDataSource(application)

    val hints = MutableLiveData<List<Hint>>()

    init {
        hintsRemoteDataSource.listHints(object : HintCallback {
            override fun onResult(hintsDomain: List<Hint>) {
                Thread {
                    hintsLocalDataSource.insertAll(hintsDomain)

                    val hintsFromDb = hintsLocalDataSource.findAll()

                    hints.postValue(hintsFromDb)
                }.start()
            }
        })

        Thread {
            val hintsFromDb = hintsLocalDataSource.findAll()
            hints.postValue(hintsFromDb)
        }.start()
    }
}
