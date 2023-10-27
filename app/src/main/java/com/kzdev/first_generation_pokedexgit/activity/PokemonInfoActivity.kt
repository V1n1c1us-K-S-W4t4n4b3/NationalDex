package com.kzdev.first_generation_pokedexgit.activity

import android.media.AudioMetadata
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
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
import java.sql.Types
import java.text.Format

class PokemonInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPokemonInfoBinding

    private lateinit var url: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        receptor()

        getData()

    }


    private fun receptor() {
        url = intent.getStringExtra("url").toString()

    }

    private fun getData() {

        //
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

                // com o ?.let tenho certesa q passa uma lista msm q vazia
                response.body()?.let {// qm chamar aqui?

                    setUpView(it)

                }
            }
        })
    }

    fun colorType(type: PokemonType): Int {

        return when (type.name) {
            "fire" -> getColor(R.color.red)
            "water" -> getColor(R.color.blue)

            else -> getColor(R.color.black)
        }

    }

    fun setUpView(pokemon: Pokemon) {


        val format = String.format("#%03d", pokemon.id)

        binding.tvNamePokemon.text = pokemon.name
        binding.tvNumbPokemon.text = format

        Glide.with(this).load(pokemon.sprites.frontDefault).into(binding.pokemonImage)


        if (pokemon.types.size >= 1) {
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