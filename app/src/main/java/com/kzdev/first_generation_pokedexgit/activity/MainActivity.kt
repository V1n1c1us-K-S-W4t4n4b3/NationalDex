package com.kzdev.first_generation_pokedexgit.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.kzdev.first_generation_pokedexgit.adapter.PokemonAdapter
import com.kzdev.first_generation_pokedexgit.adapter.SearchAdapter
import com.kzdev.first_generation_pokedexgit.databinding.ActivityMainBinding
import com.kzdev.first_generation_pokedexgit.modelrecyclerview.PokeResults
import com.kzdev.first_generation_pokedexgit.modelrecyclerview.PokemonAll
import com.kzdev.first_generation_pokedexgit.network.EndPoint
import com.kzdev.first_generation_pokedexgit.network.NetWorkUtils
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: SearchAdapter

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

    private fun setUpPokeRecyclerView(dataSet: PokemonAll) {
        val rvPokemon = binding.rvPokemon
        rvPokemon.layoutManager = LinearLayoutManager(this)
        rvPokemon.adapter = PokemonAdapter(dataSet) {
            openPokemonInfo(it)
        }
    }

    private fun setUpSearchRecyclerView(dataSet: PokemonAll) {
        val rvPokemon = binding.rvSearch
        rvPokemon.layoutManager = LinearLayoutManager(this)

        adapter = SearchAdapter(dataSet) { pokemon ->
            openPokemonInfo(pokemon)
        }
        rvPokemon.adapter = adapter

        binding.searchView.editText.addTextChangedListener {
            lifecycleScope.launch {
                findSupplierByText(it.toString())
            }
        }
    }

    private suspend fun findSupplierByText(text: String) {
        binding.progress.visibility = View.VISIBLE
        delay(500L)
        adapter.filter.filter(text)
        binding.progress.visibility = View.GONE
    }

    private fun getData() {

        val retrofitClient = NetWorkUtils.getRetrofitInstance("https://pokeapi.co/api/v2/")
        val endPoint = retrofitClient.create(EndPoint::class.java)
        val callback = endPoint.getAll()

        callback.enqueue(object : Callback<PokemonAll> {
            override fun onFailure(call: Call<PokemonAll>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(
                call: Call<PokemonAll>,
                response: Response<PokemonAll>,
            ) {
                Log.i("Test", "ok")

                response.body()?.let {
                    setUpPokeRecyclerView(it)
                    setUpSearchRecyclerView(it)
                }
            }
        })
    }
}