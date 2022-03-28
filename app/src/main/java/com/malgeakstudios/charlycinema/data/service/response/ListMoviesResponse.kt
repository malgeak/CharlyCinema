package com.malgeakstudios.charlycinema.data.service.response

import com.malgeakstudios.charlycinema.data.model.Movie

data class ListMoviesResponse (
    var page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)