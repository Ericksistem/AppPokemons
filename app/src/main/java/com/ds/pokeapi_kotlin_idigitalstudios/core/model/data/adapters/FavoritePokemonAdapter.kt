package com.ds.pokeapi_kotlin_idigitalstudios.core.model.data.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ds.pokeapi_kotlin_idigitalstudios.R
import com.ds.pokeapi_kotlin_idigitalstudios.core.database.entity.FavoritePokemon

class FavoritePokemonAdapter(
    private val favoritePokemons: List<FavoritePokemon>,
    private val onItemClick: (FavoritePokemon) -> Unit // Agrega el listener
) : RecyclerView.Adapter<FavoritePokemonAdapter.FavoriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_favorite_pokemon, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val pokemon = favoritePokemons[position]
        holder.bind(pokemon, onItemClick) // Pasa el listener al bind
    }

    override fun getItemCount(): Int = favoritePokemons.size

    class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewName: TextView = itemView.findViewById(R.id.textViewFavoriteName)
        private val imageViewPokemon: ImageView = itemView.findViewById(R.id.imageViewFavorite)

        fun bind(pokemon: FavoritePokemon, onItemClick: (FavoritePokemon) -> Unit) {
            textViewName.text = pokemon.name
            Glide.with(itemView.context)
                .load(pokemon.imageUrl)
                .into(imageViewPokemon)

            itemView.setOnClickListener { onItemClick(pokemon) }
        }
    }
}
