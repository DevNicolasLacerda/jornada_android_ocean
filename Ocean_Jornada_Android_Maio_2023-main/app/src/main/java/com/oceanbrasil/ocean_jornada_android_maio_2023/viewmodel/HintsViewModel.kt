package com.oceanbrasil.ocean_jornada_android_maio_2023.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.oceanbrasil.ocean_jornada_android_maio_2023.model.repository.HintsRepository

class HintsViewModel(application: Application) : AndroidViewModel(application) {
    private val hintsRepository = HintsRepository(application)

    val hints = hintsRepository.hints
}
