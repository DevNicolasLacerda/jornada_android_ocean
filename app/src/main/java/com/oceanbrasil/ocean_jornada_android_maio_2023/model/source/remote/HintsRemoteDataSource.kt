package com.oceanbrasil.ocean_jornada_android_maio_2023.model.source.remote

import android.util.Log
import com.oceanbrasil.ocean_jornada_android_maio_2023.model.domain.Hint
import com.oceanbrasil.ocean_jornada_android_maio_2023.model.source.remote.entities.HintsApiResult
import com.oceanbrasil.ocean_jornada_android_maio_2023.model.source.remote.service.HintsService
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HintsRemoteDataSource {
    private const val API_KEY = "patjuAoOFil6id2zw.c6383a281a1108a09e49c579b4065732e46b887881b6e9fda7e1657ea61b6b4d"

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .header("Authorization", "Bearer $API_KEY")
            val request = requestBuilder.build()
            chain.proceed(request)
        }
        .build()

    private val hintsService: HintsService

    init {
        // Prepara o Retrofit para ser usado
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.airtable.com/v0/appouUwOyyKLH6Eyz/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()

        // Prepara o HintsService para ser usado, a partir do retrofit
        hintsService = retrofit.create(HintsService::class.java)
    }

    fun listHints(callback: HintCallback) {
        // Cria a chamada que irá ser executada para listar as dicas que estão na API
        val call = hintsService.listHints()

        // Realiza a chamada para obter a lista de dicas
        call.enqueue(object : Callback<HintsApiResult> {
            // Em caso de sucesso, traz a resposta no `onResponse`
            override fun onResponse(
                call: Call<HintsApiResult>,
                response: Response<HintsApiResult>
            ) {
                // Podemos utilizar a informação contida em `response` para ter
                // os resultados
                Log.d("API_REPOSITORY", "Resposta recebida com sucesso!")

                val body: HintsApiResult? = response.body()
                if (response.isSuccessful && body != null) {
                    val records = body.records
                    val hintsApiModels = records.map { it.fields }
                    val hintsDomain = hintsApiModels.map {
                        Hint(it.id, it.name, it.latitude, it.longitude)
                    }
                    callback.onResult(hintsDomain)
                }
            }

            // Em caso de falha, traz o erro para o `onFailure`
            override fun onFailure(call: Call<HintsApiResult>, t: Throwable) {
                // Podemos utilizar a informaçõa contida em `t` para saber
                // qual foi o erro
                Log.e("API_REPOSITORY", "Resposta falhou!", t)
            }
        })
    }
}
