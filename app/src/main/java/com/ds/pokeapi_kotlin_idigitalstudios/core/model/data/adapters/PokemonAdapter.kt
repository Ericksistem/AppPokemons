package com.ds.pokeapi_kotlin_idigitalstudios.core.model.data.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ds.pokeapi_kotlin_idigitalstudios.R
import com.ds.pokeapi_kotlin_idigitalstudios.core.network.model.RequestNetworkDescripcion
import com.ds.pokeapi_kotlin_idigitalstudios.core.network.model.Result
class PokemonAdapter(
    private val pokemonList: List<RequestNetworkDescripcion>,
    private val onItemClick: (RequestNetworkDescripcion) -> Unit
) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon_card, parent, false)
        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = pokemonList[position]
        holder.bind(pokemon, onItemClick)
    }

    override fun getItemCount(): Int = pokemonList.size

    class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageViewPokemon: ImageView = itemView.findViewById(R.id.imageViewPokemon)
        private val textViewPokemonName: TextView = itemView.findViewById(R.id.pokemonName)
        private val numberPokemonCount: TextView = itemView.findViewById(R.id.pokemonNumber)

        fun bind(pokemon: RequestNetworkDescripcion, onItemClick: (RequestNetworkDescripcion) -> Unit) { // Cambiado a RequestNetworkDescripcion
            textViewPokemonName.text = pokemon.name
            numberPokemonCount.text = "#${pokemon.id}" // Ajustar según la id

            // Cargar la imagen usando Glide
            Glide.with(itemView.context)
                .load(pokemon.sprites.front_default)
                .into(imageViewPokemon)

            // Manejar el clic en la tarjeta
            itemView.setOnClickListener {
                onItemClick(pokemon)
            }
        }
    }
}
