package com.ds.pokeapi_kotlin_idigitalstudios.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon")
data class PokemonEntity(
    @PrimaryKey val name: String,
    val url: String
)

@Entity(tableName = "favorite_pokemon")
data class FavoritePokemon(
    @PrimaryKey val name: String,
    val imageUrl: String
)
