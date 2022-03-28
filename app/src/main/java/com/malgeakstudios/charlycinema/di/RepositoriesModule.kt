package com.malgeakstudios.charlycinema.di

import com.malgeakstudios.charlycinema.data.local.dao.MovieDao
import com.malgeakstudios.charlycinema.data.local.dao.SerieDao
import com.malgeakstudios.charlycinema.data.repository.MovieRepository
import com.malgeakstudios.charlycinema.data.repository.SerieRepository
import com.malgeakstudios.charlycinema.data.service.datasource.MovieRemoteDataSource
import com.malgeakstudios.charlycinema.data.service.datasource.SeriesRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {
    //Repositories Provider
    @Singleton
    @Provides
    fun provideMovieRepository(
        movieRemoteDataSource: MovieRemoteDataSource,
        movieLocalDataSource: MovieDao
    ) = MovieRepository(movieRemoteDataSource, movieLocalDataSource)

    @Singleton
    @Provides
    fun provideSerieRepository(
        serieRemoteDataSource: SeriesRemoteDataSource,
        serieLocalDataSource: SerieDao
    ) = SerieRepository(serieRemoteDataSource, serieLocalDataSource)
}