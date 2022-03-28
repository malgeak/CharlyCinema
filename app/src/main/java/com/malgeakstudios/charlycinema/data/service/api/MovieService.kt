package com.malgeakstudios.charlycinema.data.service.api

import com.malgeakstudios.charlycinema.data.model.Movie
import com.malgeakstudios.charlycinema.data.service.response.ListMoviesResponse
import com.malgeakstudios.charlycinema.data.service.response.VideosResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("movie/now_playing")
    suspend fun getMoviesPlayingNow(
        @Query(value = "language")language : String,
        @Query(value = "page") page : Int
    ): Response<ListMoviesResponse>

    @GET("movie/popular")
    suspend fun getMostPopularMovies(
        @Query(value = "language")language : String,
        @Query(value = "page") page : Int
    ): Response<ListMoviesResponse>

    @GET("movie/{id_movie}")
    suspend fun getMovieDetails(
        @Path(value = "id_movie")id : Int,
        @Query(value = "language")language : String
    ): Response<Movie>

    @GET("movie/{id_movie}/videos")
    fun getMovieVideos(
        @Path(value = "id_movie")id : Int,
        @Query(value = "language")language : String
    ): Response<VideosResponse>
}