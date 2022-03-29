package com.malgeakstudios.charlycinema.data.repository

import com.malgeakstudios.charlycinema.data.local.dao.MovieDao
import com.malgeakstudios.charlycinema.data.model.MostPopularMovies
import com.malgeakstudios.charlycinema.data.model.MoviesPlayingNow
import com.malgeakstudios.charlycinema.data.model.Videos
import com.malgeakstudios.charlycinema.data.model.VideosSerie
import com.malgeakstudios.charlycinema.data.service.datasource.MovieRemoteDataSource
import com.malgeakstudios.charlycinema.utils.getBestResponse

import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource,
    private val localDataSource : MovieDao
) {
    fun getMovie(language:String, id: Int) = getBestResponse(
        localSource = {localDataSource.getMovieDetails(id)},
        remoteSource = {remoteDataSource.getMovieDetails(language, id)},
        saveCallResult = {
            localDataSource.insertMovie(it)
        })

    fun getPlayingNowMovieList(language: String, page: Int) = getBestResponse(
        localSource = {localDataSource.getMoviesPlayingNow(page)},
        remoteSource = {remoteDataSource.getPlayingNowMovies(language, page)},
        saveCallResult = {
            val listPlayingNow: ArrayList<MoviesPlayingNow> = ArrayList()
            val page = it.page
            it.results.forEach {
                listPlayingNow.add(MoviesPlayingNow(it.id, page))
            }
            localDataSource.insertAll(it.results)
            localDataSource.insertPlayingNowList(listPlayingNow)
        }
    )

    fun getMostPopularMovieList(language: String, page: Int) = getBestResponse(
        localSource = {localDataSource.getMostPopularMovies(page)},
        remoteSource = {remoteDataSource.getMostPopularMovies(language, page)},
        saveCallResult = {
            val listMostPopular: ArrayList<MostPopularMovies> = ArrayList()
            val page = it.page
            it.results.forEach {
                listMostPopular.add(MostPopularMovies(it.id, page))
            }
            localDataSource.insertAll(it.results)
            localDataSource.insertMostPopularList(listMostPopular)
        }
    )

    fun getMovieVideos(language: String, id: Int) = getBestResponse(
        localSource = {localDataSource.getMovieVideos(id)},
        remoteSource = {remoteDataSource.getVideosMovie(language, id)},
        saveCallResult = {
            val listVideos: ArrayList<Videos> = ArrayList()
            var idMovie = id
            it.results.forEach {
                it.id_movie=idMovie
                listVideos.add(it)
            }
            localDataSource.insertVideos(listVideos)
        }
    )

}