package com.imanardhi.moviepedia.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.imanardhi.moviepedia.api.TmdbService
import com.imanardhi.moviepedia.data.model.GenreResponse
import java.io.IOException

class GenreDataSource(private val tmdbService: TmdbService, private val orderBy: String) :
    PagingSource<Int, GenreResponse>() {
    override fun getRefreshKey(state: PagingState<Int, GenreResponse>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GenreResponse> {
        return try {
            val data = tmdbService.getGenres()
            LoadResult.Page(
                data = data,
                null,
                null
            )
        } catch (throwable: Throwable) {
            var exception = throwable
            if (throwable is IOException) {
                exception = IOException("Please check internet connection")
            }
            LoadResult.Error(exception)
        }
    }
}