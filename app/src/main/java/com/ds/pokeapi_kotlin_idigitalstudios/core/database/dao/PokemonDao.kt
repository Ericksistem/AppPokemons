package com.ds.pokeapi_kotlin_idigitalstudios.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ds.pokeapi_kotlin_idigitalstudios.core.database.entity.FavoritePokemon
import com.ds.pokeapi_kotlin_idigitalstudios.core.database.entity.PokemonEntity

@Dao
interface PokemonDao {
    @Insert
    suspend fun insertPokemon(pokemon: PokemonEntity)

    @Query("SELECT * FROM pokemon")
    suspend fun getAllPokemons(): List<PokemonEntity>

    @Query("SELECT COUNT(*) FROM pokemon")
    fun getPokemonCount(): Int
}

@Dao
interface FavoritePokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavorite(pokemon: FavoritePokemon)

    @Delete
    fun removeFavorite(pokemon: FavoritePokemon)

    @Query("SELECT * FROM favorite_pokemon WHERE name = :pokemonName")
    fun getFavoriteByName(pokemonName: String): FavoritePokemon?

    @Query("SELECT * FROM favorite_pokemon")
    fun getAllFavorites(): List<FavoritePokemon>



}






