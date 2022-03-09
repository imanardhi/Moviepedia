package com.imanardhi.moviepedia.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.imanardhi.moviepedia.api.TmdbService
import com.imanardhi.moviepedia.data.datasource.GenreDataSource
import com.imanardhi.moviepedia.utils.LOAD_SIZE
import javax.inject.Inject

class Repository @Inject constructor(private val tmdbService: TmdbService) {
    fun getGenres() = Pager(
        config = PagingConfig(enablePlaceholders = false, pageSize = LOAD_SIZE),
        pagingSourceFactory = {
            GenreDataSource(tmdbService)
        }
    ).flow
}