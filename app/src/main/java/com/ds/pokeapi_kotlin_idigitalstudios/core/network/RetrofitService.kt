package com.ds.pokeapi_kotlin_idigitalstudios.core.network

import com.ds.pokeapi_kotlin_idigitalstudios.core.network.model.PokemonResponse
import com.ds.pokeapi_kotlin_idigitalstudios.core.network.model.RequestNetworkDescripcion
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import com.ds.pokeapi_kotlin_idigitalstudios.core.network.model.Result

interface PokeApiService {
    @GET("pokemon?limit=20") // Cambia el límite según tus necesidades
    suspend fun getPokemonList(): PokemonResponse

    @GET("pokemon/{id}") // Para obtener los detalles de un Pokémon
    suspend fun getPokemonDetails(@Path("id") id: Int): Result


    @GET("pokemon/{name}") // Para obtener los detalles de un Pokémon por nombre
    suspend fun getPokemonDetails(@Path("name") name: String): RequestNetworkDescripcion
}

object RetrofitInstance {
    private const val BASE_URL = "https://pokeapi.co/api/v2/"

    val api: PokeApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokeApiService::class.java)
    }
}