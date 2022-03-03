package com.imanardhi.moviepedia.di.modules

import dagger.Module

@Module(
    includes = [
        NetworkModule::class
    ]
)

class AppModule