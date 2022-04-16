package com.example.rickandmortylist.network

interface InteractorAPI {
    suspend fun getPage(page: Int): CharacterResult?

    suspend fun getCharacterById(id: Int): Character?
}