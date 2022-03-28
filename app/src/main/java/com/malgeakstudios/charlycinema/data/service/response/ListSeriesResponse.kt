package com.malgeakstudios.charlycinema.data.service.response

import com.malgeakstudios.charlycinema.data.model.Movie
import com.malgeakstudios.charlycinema.data.model.TvSeries

data class ListSeriesResponse(
    var page: Int,
    val results: List<TvSeries>,
    val total_pages: Int,
    val total_results: Int
)