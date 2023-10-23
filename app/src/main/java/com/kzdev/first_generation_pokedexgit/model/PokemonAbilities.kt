package com.kzdev.first_generation_pokedexgit.model

import com.google.gson.annotations.SerializedName

data class PokemonAbilities(

    val nameAbility: Ability,

    @SerializedName("is_hidden")
    val isHidden: Boolean,

    val slot1: Int
)
