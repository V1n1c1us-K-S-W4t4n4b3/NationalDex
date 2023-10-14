package com.kzdev.first_generation_pokedexgit.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kzdev.first_generation_pokedexgit.R
import com.kzdev.first_generation_pokedexgit.domain.Pokemon
import com.kzdev.first_generation_pokedexgit.domain.PokemonType

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.rv_pokemons)
        val bulbasaur = Pokemon(
            "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/001.png",
            1, "Bulbasaur",
            listOf(
                PokemonType("Grass"), PokemonType("Poison")
            )
        )
        val pokemons = listOf(bulbasaur,bulbasaur,bulbasaur,bulbasaur,bulbasaur,bulbasaur,bulbasaur)

        val layoutMenager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutMenager
        recyclerView.adapter = PokemonAdapter(pokemons)

    }
}