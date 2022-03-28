package com.malgeakstudios.charlycinema.data.service.datasource

import com.malgeakstudios.charlycinema.data.service.api.MovieService
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(
    private val service: MovieService
) : BaseDataSource() {

    suspend fun getMostPopularMovies(laguage : String, page: Int) =
        getResult { service.getMostPopularMovies(language = laguage, page = page) }

    suspend fun getPlayingNowMovies(language : String, page: Int) =
        getResult { service.getMoviesPlayingNow(language = language, page = page) }

    suspend fun  getVideosMovie(language: String, id : Int) =
        getResult { service.getMovieVideos(language = language, id = id) }

    suspend fun  getMovieDetails(language: String, id : Int) =
        getResult { service.getMovieDetails(language = language, id = id) }
}