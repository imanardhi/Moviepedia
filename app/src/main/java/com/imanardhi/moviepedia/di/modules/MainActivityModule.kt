package com.imanardhi.moviepedia.di.modules

import com.imanardhi.moviepedia.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector(
        modules = [

        ]
    )

    abstract fun contributeMainActivity(): MainActivity
}