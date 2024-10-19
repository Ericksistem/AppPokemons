package com.ds.pokeapi_kotlin_idigitalstudios.core.network.model


data class PokemonResponse(
    val results: List<Pokemon>
)

data class Pokemon(
    val name: String,
    val url: String
)

data class Result(
    val name: String,
    val sprites: Sprites
)

data class Sprites(
    val front_default: String
)
