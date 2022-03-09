package com.imanardhi.moviepedia.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.imanardhi.moviepedia.data.model.GenreResponse
import com.imanardhi.moviepedia.data.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GenreViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private var currentResult: Flow<PagingData<GenreResponse>>? = null

    fun getGenres(): Flow<PagingData<GenreResponse>> {
        val newResult: Flow<PagingData<GenreResponse>> =
            repository.getGenres().cachedIn(viewModelScope)
        currentResult = newResult
        return newResult
    }
}