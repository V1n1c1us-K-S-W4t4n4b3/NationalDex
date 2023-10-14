package com.kzdev.first_generation_pokedexgit.domain

data class Pokemon(
    val imagemUrl: String,
    val number: Int,
    val name: String,
    val type: List<PokemonType>
){
    val formattedNumber = number.toString().padStart(3, '0')
}

