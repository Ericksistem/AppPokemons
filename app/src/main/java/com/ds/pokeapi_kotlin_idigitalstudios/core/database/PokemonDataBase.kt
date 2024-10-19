package com.ds.pokeapi_kotlin_idigitalstudios.core.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ds.pokeapi_kotlin_idigitalstudios.core.database.dao.FavoritePokemonDao
import com.ds.pokeapi_kotlin_idigitalstudios.core.database.dao.PokemonDao
import com.ds.pokeapi_kotlin_idigitalstudios.core.database.entity.FavoritePokemon
import com.ds.pokeapi_kotlin_idigitalstudios.core.database.entity.PokemonEntity

@Database(entities = [PokemonEntity::class, FavoritePokemon::class], version = 1)
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
    abstract fun favoritePokemonDao(): FavoritePokemonDao
    companion object {
        @Volatile
        private var INSTANCE: PokemonDatabase? = null

        fun getDatabase(context: Context): PokemonDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PokemonDatabase::class.java,
                    "pokemon_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}