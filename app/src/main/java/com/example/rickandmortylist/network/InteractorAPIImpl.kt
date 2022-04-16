package com.example.rickandmortylist.network

import android.util.Log
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InteractorAPIImpl: InteractorAPI {
    private val apiRequest = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder()
                    .setLenient()
                    .create()
            )
        )
        .build()
        .create(RickMortyAPI::class.java)

    override suspend fun getPage(page: Int): CharacterResult? = apiRequest.getPage(page).body()

    override suspend fun getCharacterById(id: Int): Character? = apiRequest.getCharacterById(id).body()

    companion object {
        private const val BASE_URL =
            "https://rickandmortyapi.com/api/"
    }
}