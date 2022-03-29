package com.malgeakstudios.charlycinema.data.repository

import com.malgeakstudios.charlycinema.data.local.dao.SerieDao
import com.malgeakstudios.charlycinema.data.model.*
import com.malgeakstudios.charlycinema.data.service.datasource.MovieRemoteDataSource
import com.malgeakstudios.charlycinema.data.service.datasource.SeriesRemoteDataSource
import com.malgeakstudios.charlycinema.utils.getBestResponse
import javax.inject.Inject

class SerieRepository @Inject constructor(
    private val remoteDataSource: SeriesRemoteDataSource,
    private val localDataSource: SerieDao
) {
    fun getSerie(language: String, id: Int) =
        getBestResponse(
            localSource = { localDataSource.getSerieDetails(id) },
            remoteSource = { remoteDataSource.getSeriesDetails(language, id) },
            saveCallResult = {
                localDataSource.insertSerie(it)
            })

        fun getPlayingNowSerieList(language: String, page: Int) =
            getBestResponse(
                localSource = { localDataSource.getSeriePlayingNow(page) },
                remoteSource = { remoteDataSource.getSeriesPlayingToday(language, page) },
                saveCallResult = {
                    val listPlayingNow: ArrayList<SeriesAiringToday> = ArrayList()
                    val page = it.page
                    it.results.forEach {
                        listPlayingNow.add(SeriesAiringToday(it.id, page))
                    }
                    localDataSource.insertAll(it.results)
                    localDataSource.insertPlayingNowList(listPlayingNow)
                }
            )


        fun getMostPopularSerieList(language: String, page: Int) =
            getBestResponse(
                localSource = { localDataSource.getMostPopularSeries(page) },
                remoteSource = { remoteDataSource.getMostPopularSeries(language, page) },
                saveCallResult = {
                    val listMostPopular: ArrayList<MostPopularSeries> = ArrayList()
                    val page = it.page
                    it.results.forEach {
                        listMostPopular.add(MostPopularSeries(it.id, page))
                    }
                    localDataSource.insertAll(it.results)
                    localDataSource.insertMostPopularList(listMostPopular)
                }
            )


        fun getSerieVideos(language: String, id: Int) =
            getBestResponse(
                localSource = { localDataSource.getSerieVideos(id) },
                remoteSource = { remoteDataSource.getVideosSeries(language, id) },
                saveCallResult = {
                    val listVideos: ArrayList<VideosSerie> = ArrayList()
                    var idSerie = id
                    it.results.forEach {
                        it.id_serie=idSerie
                        listVideos.add(it)
                    }
                    localDataSource.insertVideos(listVideos)
                }
            )
}