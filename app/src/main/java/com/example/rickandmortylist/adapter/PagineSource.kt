package com.example.rickandmortylist.adapter

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmortylist.network.Character
import com.example.rickandmortylist.network.InteractorAPIImpl

class PagineSource : PagingSource<Int, Character>() {
    private val api = InteractorAPIImpl()
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val key = params.key ?: 1
        val response = api.getPage(key)
        return if (response?.results?.isEmpty() == true) {
            LoadResult.Error(Throwable("Error"))
        } else {
            LoadResult.Page(
                data = response?.results ?: emptyList(),
                nextKey = if (response?.info?.next == "null") null else key.plus(1),
                prevKey = null
            )

        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}