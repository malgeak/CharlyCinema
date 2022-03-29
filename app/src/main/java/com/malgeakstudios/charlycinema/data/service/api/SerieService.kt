package com.malgeakstudios.charlycinema.data.service.api

import com.malgeakstudios.charlycinema.data.model.Movie
import com.malgeakstudios.charlycinema.data.model.TvSeries
import com.malgeakstudios.charlycinema.data.service.response.ListMoviesResponse
import com.malgeakstudios.charlycinema.data.service.response.ListSeriesResponse
import com.malgeakstudios.charlycinema.data.service.response.VideosResponse
import com.malgeakstudios.charlycinema.data.service.response.VideosSerieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SerieService {
    @GET("tv/airing_today")
    suspend fun getSeriesPlayingNow(
        @Query(value = "language")language : String,
        @Query(value = "page") page : Int
    ): Response<ListSeriesResponse>

    @GET("tv/popular")
    suspend fun getMostPopularSeries(
        @Query(value = "language")language : String,
        @Query(value = "page") page : Int
    ): Response<ListSeriesResponse>

    @GET("tv/{id_tvseries}")
    suspend fun getSeriesDetails(
        @Path (value = "id_tvseries")id : Int,
        @Query(value = "language")language : String
    ): Response<TvSeries>

    @GET("tv/{id_tvseries}/videos")
    suspend fun getSeriesVideos(
        @Path (value = "id_tvseries")id : Int,
        @Query(value = "language")language : String
    ): Response<VideosSerieResponse>
}