package com.malgeakstudios.charlycinema.di

import com.malgeakstudios.charlycinema.data.service.api.MovieService
import com.malgeakstudios.charlycinema.data.service.api.SerieService
import com.malgeakstudios.charlycinema.data.service.datasource.MovieRemoteDataSource
import com.malgeakstudios.charlycinema.data.service.datasource.SeriesRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    //Service Provider
    @Provides
    fun provideMovieService(retrofit: Retrofit): MovieService =
        retrofit.create(MovieService::class.java)

    @Provides
    fun provideSeriesService(retrofit: Retrofit): SerieService =
        retrofit.create(SerieService::class.java)

    //DataSource Provider
    @Singleton
    @Provides
    fun provideMovieRemoteDataSource(movieService: MovieService) =
        MovieRemoteDataSource(movieService)

    @Singleton
    @Provides
    fun provideServiceRemoteDataSource(serieService: SerieService) =
        SeriesRemoteDataSource(serieService)
}