package com.oceanbrasil.ocean_jornada_android_maio_2023.model.source.remote.service

import com.oceanbrasil.ocean_jornada_android_maio_2023.model.source.remote.entities.HintsApiResult
import retrofit2.Call
import retrofit2.http.GET

interface HintsService {
    @GET("tblCw2tYfnYbbgIel")
    fun listHints(): Call<HintsApiResult>
}
