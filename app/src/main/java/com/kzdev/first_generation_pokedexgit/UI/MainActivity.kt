package com.kzdev.first_generation_pokedexgit.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.kzdev.first_generation_pokedexgit.databinding.ActivityMainBinding
import com.kzdev.first_generation_pokedexgit.model.Pokemon
import com.kzdev.first_generation_pokedexgit.network.EndPoint
import com.kzdev.first_generation_pokedexgit.network.NetWorkUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getData()

    }

    private fun setUpRecyclerView(dataSet: List<Pokemon>) {

        val rvPokemon = binding.rvPokemons

        rvPokemon.layoutManager = LinearLayoutManager(this)
        rvPokemon.adapter = PokemonAdapter(dataSet)

    }

    fun getData() {

        val retrofitClient = NetWorkUtils.getRetrofitInstance(
            " https://pokeapi.co/api/v2/")

        val endPoint = retrofitClient.create(EndPoint::class.java)
        val callback = endPoint.getAll()

        callback.enqueue(object : Callback<List<Pokemon>> {
            override fun onFailure(call: Call<List<Pokemon>>, t: Throwable) {
              //  Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
                t.printStackTrace()

            }

            override fun onResponse(
                call: Call<List<Pokemon>>,
                response: Response<List<Pokemon>>,
            ) {
                Log.i("Test", "ok")

                // com o ?.let tenho certesa q passa uma lista msm q vazia
                response.body()?.let {// qm chamar aqui?
                    setUpRecyclerView(it)
                }
            }
        })
    }
}
