package com.kzdev.first_generation_pokedexgit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kzdev.first_generation_pokedexgit.databinding.PokemonItemBinding
import com.kzdev.first_generation_pokedexgit.modelrecyclerview.PokeResults
import com.kzdev.first_generation_pokedexgit.modelrecyclerview.PokemonAll

class PokemonAdapter(
    private val dataSet: PokemonAll,
    private val onItemClicked: (PokeResults) -> Unit,
) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PokemonItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val name = dataSet.results[position].name
        val format = String.format("#%04d", position + 1)

        viewHolder.binding.tvNumbPokemon.text = format
        viewHolder.binding.tvNamePokemon.text = name

        viewHolder.itemView.setOnClickListener {
            onItemClicked(dataSet.results[position])
        }
    }

    override fun getItemCount() = dataSet.results.size

    class ViewHolder(val binding: PokemonItemBinding) : RecyclerView.ViewHolder(binding.root)
}