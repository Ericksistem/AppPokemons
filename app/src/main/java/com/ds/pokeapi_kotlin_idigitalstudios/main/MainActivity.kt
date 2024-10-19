package com.ds.pokeapi_kotlin_idigitalstudios.main


import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ds.pokeapi_kotlin_idigitalstudios.R
import com.ds.pokeapi_kotlin_idigitalstudios.core.model.data.adapters.PokemonAdapter
import com.ds.pokeapi_kotlin_idigitalstudios.core.model.data.adapters.PokemonDetailActivity
import com.ds.pokeapi_kotlin_idigitalstudios.core.network.RetrofitInstance
import com.ds.pokeapi_kotlin_idigitalstudios.core.network.model.RequestNetworkDescripcion
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var pokemonAdapter: PokemonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activitymain)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        // Iniciar la carga de datos
        loadPokemonData()
    }

    private fun loadPokemonData() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val pokemonList = mutableListOf<RequestNetworkDescripcion>() // Cambiado a RequestNetworkDescripcion
                val response = RetrofitInstance.api.getPokemonList()

                for (pokemon in response.results) {
                    // Obtén los detalles del Pokémon usando su nombre
                    val pokemonDetails = RetrofitInstance.api.getPokemonDetails(pokemon.name)
                    pokemonList.add(pokemonDetails) // Aquí sigue siendo correcto
                }

                // Configurar el adaptador y manejar el clic
                pokemonAdapter = PokemonAdapter(pokemonList) { selectedPokemon -> // Cambiado a RequestNetworkDescripcion
                    val intent = Intent(this@MainActivity, PokemonDetailActivity::class.java)
                    intent.putExtra("pokemon_name", selectedPokemon.name)
                    intent.putExtra("pokemon_image", selectedPokemon.sprites.front_default)
                    startActivity(intent)
                }

                recyclerView.adapter = pokemonAdapter

            } catch (e: Exception) {
                showErrorDialog(
                    "Error",
                    "No se pudo conectar a PokeAPI. Verifique su conexión a Internet."
                )
            }
        }
    }
    // Método para mostrar el AlertDialog
    private fun showErrorDialog(title: String, message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
            .setMessage(message)
            .setPositiveButton("Aceptar") { dialog: DialogInterface, _: Int ->
                dialog.dismiss() // Cierra el diálogo al hacer clic en "Aceptar"
            }
            .setCancelable(true) // Permite cerrar el diálogo tocando fuera de él
        val dialog = builder.create()
        dialog.show() // Muestra el diálogo
    }
}
