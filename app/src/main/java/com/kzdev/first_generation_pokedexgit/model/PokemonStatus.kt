package com.kzdev.first_generation_pokedexgit.model

data class PokemonStatus(
    val baseStatus: Int,
    val effort:Int,
    val stat:List<PokemonStat>
)
