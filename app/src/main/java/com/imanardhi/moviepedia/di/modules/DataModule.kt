package com.imanardhi.moviepedia.di.modules

import com.imanardhi.moviepedia.api.TmdbService
import com.imanardhi.moviepedia.data.repository.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DataModule {
    @Singleton
    @Provides
    fun providesRepository(tmdbService: TmdbService): Repository = Repository(tmdbService)
}