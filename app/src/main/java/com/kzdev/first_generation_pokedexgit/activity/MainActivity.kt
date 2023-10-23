package com.kzdev.first_generation_pokedexgit.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.kzdev.first_generation_pokedexgit.adapter.PokemonAdapter
import com.kzdev.first_generation_pokedexgit.databinding.ActivityMainBinding
import com.kzdev.first_generation_pokedexgit.modelrecyclerview.PokeResults
import com.kzdev.first_generation_pokedexgit.modelrecyclerview.PokemonAll
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

    private fun openPokemonInfo(pokeResults: PokeResults) {
        val intent = Intent(this@MainActivity, PokemonInfoActivity::class.java)

        intent.putExtra("name", pokeResults.name)
        intent.putExtra("url", pokeResults.url)

        startActivity(intent)
    }

    private fun setUpRecyclerView(dataSet: PokemonAll) {

        val rvPokemon = binding.rvPokemons

        rvPokemon.layoutManager = LinearLayoutManager(this)
        rvPokemon.adapter = PokemonAdapter(dataSet) {

            openPokemonInfo(it)

        }

    }

    private fun getData() {

            // declarar a url base
            val retrofitClient = NetWorkUtils.getRetrofitInstance("https://pokeapi.co/api/v2/")

            val endPoint = retrofitClient.create(EndPoint::class.java)
            val callback = endPoint.getAll()

            callback.enqueue(object : Callback<PokemonAll> {
                override fun onFailure(call: Call<PokemonAll>, t: Throwable) {
                    //  Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
                    t.printStackTrace()
                }

                override fun onResponse(
                    call: Call<PokemonAll>,
                    response: Response<PokemonAll>,
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
