package com.kzdev.first_generation_pokedexgit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.kzdev.first_generation_pokedexgit.databinding.PokemonItemSearchBinding
import com.kzdev.first_generation_pokedexgit.modelrecyclerview.PokeResults
import com.kzdev.first_generation_pokedexgit.modelrecyclerview.PokemonAll

class SearchAdapter (
    private val dataSet: PokemonAll,
    private val onItemClicked: (PokeResults) -> Unit,
    ) : RecyclerView.Adapter<SearchAdapter.ViewHolder>(), Filterable {

    private var filteredDataSet: MutableList<PokeResults> = mutableListOf()

    inner class ViewHolder(val binding: PokemonItemSearchBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = PokemonItemSearchBinding.inflate(inflater, parent, false)
            return ViewHolder(binding)
        }

        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

            val name = filteredDataSet[position].name
            viewHolder.binding.tvNamePokemon.text = name
            viewHolder.itemView.setOnClickListener {
                onItemClicked(filteredDataSet[position])
            }
        }

        override fun getItemCount() = filteredDataSet.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filterPattern = constraint.toString().trim()

                val filteredList = dataSet.results.filter {
                    it.name.contains(filterPattern, true)
                }

                filteredDataSet.clear()
                filteredDataSet.addAll(filteredList)

                val filterResults = FilterResults()
                filterResults.values = filteredDataSet

                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                notifyDataSetChanged()
            }
        }
    }
}