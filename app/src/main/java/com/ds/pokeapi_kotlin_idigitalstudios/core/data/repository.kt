package com.ds.pokeapi_kotlin_idigitalstudios.core.data

import com.ds.pokeapi_kotlin_idigitalstudios.core.network.PokeApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Configuración de Retrofit
val retrofit = Retrofit.Builder()
    .baseUrl("https://pokeapi.co/api/v2/") // URL base de la PokeAPI
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val pokeApiService: PokeApiService = retrofit.create(PokeApiService::class.java)
