package com.imanardhi.moviepedia.di.modules

import com.imanardhi.moviepedia.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector(
        modules = [
            FragmentBuildersModule::class
        ]
    )

    abstract fun contributeMainActivity(): MainActivity
}