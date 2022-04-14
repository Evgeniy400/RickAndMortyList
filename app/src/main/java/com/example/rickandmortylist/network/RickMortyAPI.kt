package com.example.rickandmortylist.network

import retrofit2.http.GET

interface RickMortyAPI {
    @GET()
    suspend fun getPage()
}