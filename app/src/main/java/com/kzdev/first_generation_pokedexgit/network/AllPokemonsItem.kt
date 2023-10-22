package com.kzdev.first_generation_pokedexgit.network

import com.kzdev.first_generation_pokedexgit.modelrecyclerview.PokemonAll
import retrofit2.Call
import retrofit2.http.GET

interface AllPokemonsItem {

    @GET("pokemon?limit=100000&offset=0")
    fun getAll(): Call<List<PokemonAll>>
}