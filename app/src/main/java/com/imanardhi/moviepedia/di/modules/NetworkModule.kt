package com.imanardhi.moviepedia.di.modules

import android.app.Application
import com.imanardhi.moviepedia.BuildConfig
import com.imanardhi.moviepedia.api.TmdbService
import com.imanardhi.moviepedia.utils.Config
import dagger.Module
import dagger.Provides
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
object NetworkModule {
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Singleton
    @Provides
    fun providesOkhttp(cache: Cache): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .addNetworkInterceptor(cacheInterceptor)
            .cache(cache)
            .addInterceptor { chain: Interceptor.Chain ->
                val newRequest = chain.request().newBuilder()
                    //.addHeader("Authorization", "Client-ID" + Config.API_TMDB)
                    .build()
                chain.proceed(newRequest)
            }
        if (BuildConfig.DEBUG) builder.addInterceptor(loggingInterceptor)

        return builder.build()
    }

    @Singleton
    @Provides
    fun providesRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Config.BASE_URL_TMDB)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesApiService(retrofit: Retrofit): TmdbService {
        return retrofit.create(TmdbService::class.java)
    }

    @Singleton
    @Provides
    fun providesCache(app: Application): Cache {
        return Cache(File(app.applicationContext.cacheDir, "tmdb_cache"), 10 * 1024 * 1024)
    }

    private val cacheInterceptor = object : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val response: Response = chain.proceed(chain.request())
            val cacheControl = CacheControl.Builder()
                .maxAge(30, TimeUnit.DAYS)
                .build()

            return response.newBuilder()
                .header("Cache-Control", cacheControl.toString())
                .build()
        }

    }
}