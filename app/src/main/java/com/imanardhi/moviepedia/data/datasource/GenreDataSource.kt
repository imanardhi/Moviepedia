package com.imanardhi.moviepedia.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.imanardhi.moviepedia.api.TmdbService
import com.imanardhi.moviepedia.data.model.GenreResponse
import com.imanardhi.moviepedia.utils.STARTING_PAGE
import java.io.IOException

class GenreDataSource(private val tmdbService: TmdbService, private val orderBy: String) :
    PagingSource<Int, GenreResponse>() {
    override fun getRefreshKey(state: PagingState<Int, GenreResponse>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GenreResponse> {
        val page = params.key ?: STARTING_PAGE

        return try {
            val data = tmdbService.getGenres(page, "id")
            LoadResult.Page(
                data = data,
                prevKey = if (page == STARTING_PAGE) null else page - 1,
                nextKey = if (data.isEmpty()) null else page + 1
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