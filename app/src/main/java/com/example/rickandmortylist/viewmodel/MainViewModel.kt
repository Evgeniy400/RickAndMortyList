package com.example.rickandmortylist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.rickandmortylist.adapter.PagineSource

class MainViewModel: ViewModel() {

    val flow = Pager(
        PagingConfig(pageSize = 20)
    ) {
        PagineSource()
    }.flow
        .cachedIn(viewModelScope)
}