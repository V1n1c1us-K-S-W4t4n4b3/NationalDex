package com.kzdev.first_generation_pokedexgit.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.kzdev.first_generation_pokedexgit.R
import com.kzdev.first_generation_pokedexgit.databinding.ActivityPokemonInfoBinding
import com.kzdev.first_generation_pokedexgit.model.Pokemon
import com.kzdev.first_generation_pokedexgit.model.PokemonType
import com.kzdev.first_generation_pokedexgit.network.EndPoint
import com.kzdev.first_generation_pokedexgit.network.NetWorkUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonInfoActivity : AppCompatActivity() {

    private val binding by lazy { ActivityPokemonInfoBinding.inflate(layoutInflater) }

    private lateinit var url: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        receptor()
        getData()
    }

    private fun receptor() {
        url = intent.getStringExtra("url").toString()
    }

    private fun getData() {

        val retrofitClient = NetWorkUtils.getRetrofitInstance("https://pokeapi.co/api/v2/")
        val endPoint = retrofitClient.create(EndPoint::class.java)
        val callback = endPoint.getPokemon(url)

        callback.enqueue(object : Callback<Pokemon> {
            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                //  Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
                t.printStackTrace()
            }

            override fun onResponse(
                call: Call<Pokemon>,
                response: Response<Pokemon>,
            ) {
                Log.i("Test", "ok")

                response.body()?.let {
                    setUpView(it)
                }
            }
        })
    }

    private fun colorType(type: PokemonType): Int {

        return when (type.name) {
            "fairy" -> getColor(R.color.light_pink)
            "steel" -> getColor(R.color.gray)
            "dark" -> getColor(R.color.brown)
            "dragon" -> getColor(R.color.dark_black_purple)
            "ghost" -> getColor(R.color.dark_purple)
            "rock" -> getColor(R.color.dark_yellow)
            "bug" -> getColor(R.color.light_green)
            "psychic" -> getColor(R.color.pink)
            "flying" -> getColor(R.color.light_purple)
            "ground" -> getColor(R.color.light_yellow)
            "poison" -> getColor(R.color.poison)
            "fight" -> getColor(R.color.red)
            "ice" -> getColor(R.color.ice)
            "grass" -> getColor(R.color.green)
            "electric" -> getColor(R.color.yellow)
            "water" -> getColor(R.color.blue)
            "fire" -> getColor(R.color.orange)
            "normal" -> getColor(R.color.beige)

            else -> getColor(R.color.black)
        }
    }

    fun setUpView(pokemon: Pokemon) {

        val format = String.format("#%04d", pokemon.id)
        binding.tvNumbPokemon.text = format

        binding.toolbar.setNavigationOnClickListener { finish() }

        binding.toolbar.title = pokemon.name

        Glide.with(this).load(pokemon.sprites.frontDefault).into(binding.pokemonImage)

        if (pokemon.types.isNotEmpty()) {
            binding.tvType1.text = pokemon.types[0].type.name
            binding.tvType1.setBackgroundColor(colorType(pokemon.types[0].type))
            binding.tvType2.visibility = View.GONE
        }

        if (pokemon.types.size >= 2) {
            binding.tvType2.text = pokemon.types[1].type.name
            binding.tvType2.setBackgroundColor(colorType(pokemon.types[1].type))
            binding.tvType2.visibility = View.VISIBLE
        }
    }
}