package com.ds.pokeapi_kotlin_idigitalstudios.core.network.model

data class RequestNetworkDescripcion(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val abilities: List<Ability>,
    val stats: List<Stat>,
    val types: List<Type>,
    val sprites: Sprites
)

data class Ability(
    val ability: AbilityDetails
)

data class AbilityDetails(
    val name: String
)

data class Stat(
    val base_stat: Int,
    val stat: StatDetails
)

data class StatDetails(
    val name: String
)

data class Type(
    val type: TypeDetails
)

data class TypeDetails(
    val name: String
)
