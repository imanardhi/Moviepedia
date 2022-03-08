package com.imanardhi.moviepedia.api

import com.imanardhi.moviepedia.data.model.GenreResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbService {
    @GET("genre/movie/list")
    suspend fun getGenres(
        @Query("page") page: Int,
        @Query("language") language: String
    ): List<GenreResponse>
}