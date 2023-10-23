package com.kzdev.first_generation_pokedexgit.network

import com.kzdev.first_generation_pokedexgit.model.Pokemon
import com.kzdev.first_generation_pokedexgit.modelrecyclerview.PokemonAll
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface EndPoint {

    // com @Get declara para utilizar o conteudo posterior a ele 
    @GET("pokemon?limit=100000&offset=0")
    fun getAll(): Call<PokemonAll>

    // com @Url declara q deve ser utilizado a url completa do item clicado
    @GET
    fun getPokemon(@Url url:String): Call<Pokemon>
}