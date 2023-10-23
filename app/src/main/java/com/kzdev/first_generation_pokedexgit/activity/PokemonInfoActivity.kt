package com.kzdev.first_generation_pokedexgit.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.kzdev.first_generation_pokedexgit.databinding.ActivityPokemonInfoBinding
import com.kzdev.first_generation_pokedexgit.model.Pokemon
import com.kzdev.first_generation_pokedexgit.network.EndPoint
import com.kzdev.first_generation_pokedexgit.network.NetWorkUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

    fun setUpView(pokemon: Pokemon) {

        binding.tvNamePokemon.text = pokemon.name
        binding.tvNumbPokemon.text = pokemon.id.toString()

        if (pokemon.types.size >= 1) {
            binding.tvType1.text = pokemon.types[0].type.name
            binding.tvType2.visibility = View.GONE
        }

        if (pokemon.types.size >= 2) {
            binding.tvType2.text = pokemon.types[1].type.name
            binding.tvType2.visibility = View.VISIBLE

        }
    }
}