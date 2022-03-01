package com.imanardhi.moviepedia.di.modules

import com.imanardhi.moviepedia.view.GenreFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeGenreFragment(): GenreFragment
}