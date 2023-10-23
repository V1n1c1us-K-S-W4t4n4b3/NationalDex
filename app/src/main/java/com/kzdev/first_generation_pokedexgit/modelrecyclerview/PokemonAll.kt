package com.kzdev.first_generation_pokedexgit.modelrecyclerview

data class PokemonAll(

    val count: Int,
    val next: String,
    val previous: String,
    val results: List<PokeResults>
)