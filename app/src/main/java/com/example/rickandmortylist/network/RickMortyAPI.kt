package com.example.rickandmortylist.network


import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickMortyAPI {

    @GET("character")
    suspend fun getPage(@Query("page") page: Int): Response<CharacterResult>


    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): Response<Character>

}