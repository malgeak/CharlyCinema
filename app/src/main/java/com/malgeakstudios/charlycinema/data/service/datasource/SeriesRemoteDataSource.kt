package com.malgeakstudios.charlycinema.data.service.datasource

import com.malgeakstudios.charlycinema.data.service.api.SerieService
import javax.inject.Inject

class SeriesRemoteDataSource @Inject constructor(
    private val service: SerieService
): BaseDataSource() {
    suspend fun getMostPopularSeries(laguage : String, page: Int) =
        getResult { service.getMostPopularSeries(language = laguage, page = page) }

    suspend fun getSeriesPlayingToday(language : String, page: Int) =
        getResult { service.getSeriesPlayingNow(language = language, page = page) }

    suspend fun  getVideosSeries(language: String, id : Int) =
        getResult {service.getSeriesVideos(language = language, id = id)}

    suspend fun  getSeriesDetails(language: String, id : Int) =
        getResult { service.getSeriesDetails(language = language, id = id) }
}