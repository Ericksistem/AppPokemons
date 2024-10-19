package com.ds.pokeapi_kotlin_idigitalstudios.core.model.data.adapters

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ds.pokeapi_kotlin_idigitalstudios.R
import com.ds.pokeapi_kotlin_idigitalstudios.core.database.PokemonDatabase
import com.ds.pokeapi_kotlin_idigitalstudios.core.database.dao.FavoritePokemonDao
import com.ds.pokeapi_kotlin_idigitalstudios.core.database.entity.FavoritePokemon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoritesActivity : AppCompatActivity() {

    private lateinit var favoritePokemonDao: FavoritePokemonDao
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FavoritePokemonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.favorite_activity)

        favoritePokemonDao = PokemonDatabase.getDatabase(this).favoritePokemonDao()
        recyclerView = findViewById(R.id.recyclerViewFavorites)
        recyclerView.layoutManager = LinearLayoutManager(this)

        loadFavorites()
    }

    private fun loadFavorites() {
        CoroutineScope(Dispatchers.IO).launch {
            val favoritePokemons: List<FavoritePokemon> = favoritePokemonDao.getAllFavorites()
            withContext(Dispatchers.Main) {
                adapter = FavoritePokemonAdapter(favoritePokemons) { pokemon ->
                    sharePokemon(pokemon.name, pokemon.imageUrl) // Comparte el Pokémon
                }
                recyclerView.adapter = adapter
            }
        }
    }

    private fun sharePokemon(name: String?, imageUrl: String?) {
        if (name != null && imageUrl != null) {
            val shareText = "¡Mira este Pokémon! $name \n$imageUrl"
            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, shareText)
                putExtra(Intent.EXTRA_STREAM, Uri.parse(imageUrl))
                type = "image/jpeg"
            }

            if (shareIntent.resolveActivity(packageManager) != null) {
                startActivity(Intent.createChooser(shareIntent, "Compartir Pokémon"))
            }
        }
    }
}
