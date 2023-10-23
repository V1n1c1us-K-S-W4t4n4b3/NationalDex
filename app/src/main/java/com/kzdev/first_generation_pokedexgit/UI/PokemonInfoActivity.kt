package com.kzdev.first_generation_pokedexgit.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kzdev.first_generation_pokedexgit.databinding.ActivityPokemonInfoBinding

class PokemonInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPokemonInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}