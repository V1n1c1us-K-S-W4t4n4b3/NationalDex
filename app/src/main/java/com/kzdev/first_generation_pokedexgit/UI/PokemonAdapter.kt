package com.kzdev.first_generation_pokedexgit.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kzdev.first_generation_pokedexgit.R
import com.kzdev.first_generation_pokedexgit.model.Pokemon
import com.kzdev.first_generation_pokedexgit.modelrecyclerview.PokeResults
import com.kzdev.first_generation_pokedexgit.modelrecyclerview.PokemonAll

class PokemonAdapter(private val dataSet: PokemonAll, private val initPokeInfo: (PokeResults) -> Unit) :
    RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        // val id: TextView

        // val ivPokemon: ImageView
        val tvName: TextView
        // val tvType1: TextView
        //val tvType2: TextView

        init {

            // id = view.findViewById(R.id.tv_numb_pokemon)
            // ivPokemon = view.findViewById(R.id.pokemon_image)
            tvName = view.findViewById(R.id.tv_name_pokemon)
            // tvType1 = view.findViewById(R.id.tv_type1)
            // tvType2 = view.findViewById(R.id.tv_type2)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.pokemon_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // viewHolder.id.text = dataSet[position].id.toString()
        viewHolder.tvName.text = dataSet.results[position].name
        // viewHolder.ivPokemon.imageMatrix = dataSet[position].image.frontDefault
        //viewHolder.tvType1.text = dataSet[position].types.slot1.name
        // viewHolder.tvType2.text = dataSet[position].types.slot2.name
        viewHolder.itemView.setOnClickListener {

            initPokeInfo(dataSet.results[position])

        }
    }

    override fun getItemCount() = dataSet.results.size
}