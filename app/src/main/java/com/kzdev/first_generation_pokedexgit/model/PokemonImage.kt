package com.kzdev.first_generation_pokedexgit.model

import com.google.gson.annotations.SerializedName

data class PokemonImage(


    @SerializedName("front_default")
    val frontDefault: String

)
