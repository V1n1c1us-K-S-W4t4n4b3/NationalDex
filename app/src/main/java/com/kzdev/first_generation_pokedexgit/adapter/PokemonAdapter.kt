package com.kzdev.first_generation_pokedexgit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kzdev.first_generation_pokedexgit.R
import com.kzdev.first_generation_pokedexgit.model.Pokemon
import com.kzdev.first_generation_pokedexgit.modelrecyclerview.PokeResults
import com.kzdev.first_generation_pokedexgit.modelrecyclerview.PokemonAll

class PokemonAdapter(
    private val dataSet: PokemonAll,
    private val initPokeInfo: (PokeResults) -> Unit,
) :
    RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val id: TextView
        val tvName: TextView

        init {

            id = view.findViewById(R.id.tv_numb_pokemon)
            tvName = view.findViewById(R.id.tv_name_pokemon)

        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.pokemon_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val format = String.format("#%04d", position + 1)
        viewHolder.id.text = format

        viewHolder.tvName.text = dataSet.results[position].name
        viewHolder.itemView.setOnClickListener {

            initPokeInfo(dataSet.results[position])
        }
    }

    override fun getItemCount() = dataSet.results.size
}