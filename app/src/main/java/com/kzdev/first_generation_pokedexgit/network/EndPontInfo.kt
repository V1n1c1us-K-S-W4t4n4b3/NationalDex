package com.kzdev.first_generation_pokedexgit.network

import com.kzdev.first_generation_pokedexgit.model.Pokemon
import retrofit2.Call
import retrofit2.http.GET

interface EndPontInfo {
    @GET("/")
    fun getPokemon(): Call<Pokemon>
}