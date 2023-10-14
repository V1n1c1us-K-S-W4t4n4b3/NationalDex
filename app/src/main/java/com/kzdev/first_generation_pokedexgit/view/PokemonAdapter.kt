package com.kzdev.first_generation_pokedexgit.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kzdev.first_generation_pokedexgit.R
import com.kzdev.first_generation_pokedexgit.domain.Pokemon

class PokemonAdapter(
    private val items: List<Pokemon>
) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_item, parent, false)

        return ViewHolder(view)

    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.bindView(item)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(item: Pokemon) = with(itemView) {
            val ivPokemon = findViewById<ImageView>(R.id.pokemon_image)
            val tvNumber = findViewById<TextView>(R.id.tv_numb_pokemon)
            val tvName = findViewById<TextView>(R.id.tv_name_pokemon)
            val tvType1 = findViewById<TextView>(R.id.tv_type1)
            val tvType2 = findViewById<TextView>(R.id.tv_type2)

            tvNumber.text = "Nº ${item.formattedNumber}"
            tvName.text = item.name
            tvType1.text = item.type[0].name
            tvType2.text = item.type[0].name

            if (item.type.size > 1) {
                tvType2.visibility = View.VISIBLE
                tvType2.text = item.type[1].name

            } else {
                tvType2.visibility = View.GONE
            }
        }
    }
}