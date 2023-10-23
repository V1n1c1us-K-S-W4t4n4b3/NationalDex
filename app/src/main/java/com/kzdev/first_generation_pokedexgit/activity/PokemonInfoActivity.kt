package com.kzdev.first_generation_pokedexgit.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.kzdev.first_generation_pokedexgit.databinding.ActivityPokemonInfoBinding
import com.kzdev.first_generation_pokedexgit.model.Pokemon
import com.kzdev.first_generation_pokedexgit.network.EndPontInfo
import com.kzdev.first_generation_pokedexgit.network.NetWorkUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPokemonInfoBinding

    private lateinit var url : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getData()

        receptor()

    }


    private fun receptor(){

        val name = intent.getStringExtra("name")
        url = intent.getStringExtra("url").toString()

        binding.textPokemon.text = name

    }

    private fun getData() {

        val retrofitClient = NetWorkUtils.getRetrofitInstance(url)

        val endPoint = retrofitClient.create(EndPontInfo::class.java)
        val callback = endPoint.getPokemon()

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


                   // binding.textPokemon.text =
                }
            }
        })
    }


}