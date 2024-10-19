package com.ds.pokeapi_kotlin_idigitalstudios.core.model.data.adapters

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.ds.pokeapi_kotlin_idigitalstudios.R
import com.ds.pokeapi_kotlin_idigitalstudios.core.database.PokemonDatabase
import com.ds.pokeapi_kotlin_idigitalstudios.core.database.dao.FavoritePokemonDao
import com.ds.pokeapi_kotlin_idigitalstudios.core.database.entity.FavoritePokemon
import com.ds.pokeapi_kotlin_idigitalstudios.core.network.RetrofitInstance
import com.ds.pokeapi_kotlin_idigitalstudios.core.network.model.RequestNetworkDescripcion
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PokemonDetailActivity : AppCompatActivity() {

    private lateinit var favoritePokemonDao: FavoritePokemonDao
    private lateinit var scope: CoroutineScope

    @SuppressLint("MissingInflatedId", "WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_detail)

        // Inicializar el DAO y el scope de corutinas
        favoritePokemonDao = PokemonDatabase.getDatabase(this).favoritePokemonDao()
        scope = CoroutineScope(Dispatchers.Main)

        // Obtener los extras del Intent
        val pokemonName = intent.getStringExtra("pokemon_name")
        val pokemonImage = intent.getStringExtra("pokemon_image")

        // Buscar las vistas en el layout
        val textViewName = findViewById<TextView>(R.id.textViewPokemonName)
        val imageViewPokemon = findViewById<ImageView>(R.id.imageViewPokemonDetail)
        val imageViewFavorite = findViewById<ImageView>(R.id.imageViewFavorite)

        // Asignar el nombre del Pokémon con el prefijo
        textViewName.text = getString(R.string.name_prefix, pokemonName)

        // Llamar al método para obtener los detalles del Pokémon
        fetchPokemonDetails(pokemonName)

        // Cargar la imagen usando Glide
        Glide.with(this)
            .load(pokemonImage)
            .into(imageViewPokemon)

        // Estado inicial del favorito: consultando la base de datos
        scope.launch(Dispatchers.IO) {
            val favoritePokemon = pokemonName?.let { favoritePokemonDao.getFavoriteByName(it) }
            val isFavorite = favoritePokemon != null

            // Cambiar el ícono en el hilo principal
            withContext(Dispatchers.Main) {
                updateFavoriteIcon(imageViewFavorite, isFavorite)

                // Manejar el clic en el ícono de favorito en el hilo principal
                imageViewFavorite.setOnClickListener {
                    scope.launch(Dispatchers.IO) {
                        if (isFavorite) {
                            // Remover de favoritos
                            favoritePokemon?.let { favoritePokemonDao.removeFavorite(it) }
                            withContext(Dispatchers.Main) {
                                updateFavoriteIcon(imageViewFavorite, false)
                            }
                        } else {
                            // Agregar a favoritos
                            if (pokemonName != null && pokemonImage != null) {
                                favoritePokemonDao.addFavorite(FavoritePokemon(pokemonName, pokemonImage))
                                withContext(Dispatchers.Main) {
                                    updateFavoriteIcon(imageViewFavorite, true)
                                }
                            }
                        }
                    }
                }
            }
        }

        // Botón para regresar
        val buttonBack = findViewById<Button>(R.id.buttonBack)
        buttonBack.setOnClickListener {
            finish() // Esto cerrará la actividad actual y regresará a la anterior
        }

        // Botón para ver favoritos
        val buttonFavorites = findViewById<Button>(R.id.Safe)
        buttonFavorites.setOnClickListener {
            val intent = Intent(this, FavoritesActivity::class.java)
            startActivity(intent)
        }
    }

    private fun fetchPokemonDetails(pokemonName: String?) {
        if (pokemonName != null) {
            scope.launch(Dispatchers.IO) {
                try {
                    // Realizar la llamada a la API para obtener los detalles
                    val pokemonDetails = RetrofitInstance.api.getPokemonDetails(pokemonName)

                    // Volver al hilo principal para actualizar la UI
                    withContext(Dispatchers.Main) {
                        populatePokemonDetails(pokemonDetails)
                    }
                } catch (e: Exception) {
                    e.printStackTrace() // Manejo de errores en la llamada a la API
                }
            }
        }
    }
    private fun populatePokemonDetails(pokemon: RequestNetworkDescripcion) {
        val imageViewPokemon = findViewById<ImageView>(R.id.imageViewPokemonDetail)
        Glide.with(this)
            .load(pokemon.sprites.front_default)
            .into(imageViewPokemon)
        val textViewId = findViewById<TextView>(R.id.textViewId)
        textViewId.text = "ID Número: ${pokemon.id}"
        val textViewName = findViewById<TextView>(R.id.textViewPokemonName)
        textViewName.text = pokemon.name.capitalize()
        val textViewStats = findViewById<TextView>(R.id.textViewStatistics)
        textViewStats.text = pokemon.stats.joinToString { "${it.stat.name}: ${it.base_stat}" }
        val textViewHeight = findViewById<TextView>(R.id.textViewPokemonHeight)
        textViewHeight.text = "Altura: ${pokemon.height}"
        val textViewWeight = findViewById<TextView>(R.id.textViewPokemonWeight)
        textViewWeight.text = "Peso: ${pokemon.weight}"
        val habilidad1 = findViewById<TextView>(R.id.habilidad1)
        habilidad1.text = pokemon.abilities[0].ability.name
        val habilidad2 = findViewById<TextView>(R.id.textHabildiad2)
        if (pokemon.abilities.size > 1) {
            habilidad2.text = pokemon.abilities[1].ability.name
        } else {
            habilidad2.text = ""
        }
        val movimiento1 = findViewById<TextView>(R.id.movimeinto1)
        movimiento1.text = pokemon.types[0].type.name
    }
    private fun updateFavoriteIcon(imageView: ImageView, isFavorite: Boolean) {
        if (isFavorite) {
            imageView.setImageResource(R.drawable.stars_24dp_5f6368_fill0_wght400_grad0_opsz24)
        } else {
            imageView.setImageResource(R.drawable.heart_plus_24dp_5f6368_fill0_wght400_grad0_opsz24)
        }
    }
}