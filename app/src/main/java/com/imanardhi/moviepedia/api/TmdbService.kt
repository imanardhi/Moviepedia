package com.imanardhi.moviepedia.api

import com.imanardhi.moviepedia.data.model.GenreResponse
import retrofit2.http.GET

interface TmdbService {
    @GET("genre/movie/list")
    suspend fun getGenres(): List<GenreResponse>
}