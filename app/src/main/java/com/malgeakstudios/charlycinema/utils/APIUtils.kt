package com.malgeakstudios.charlycinema.utils

object APIUtils {
    enum class TYPE_MEDIA {
        movies, series
    }
    const val API_BASE_URL = "https://api.themoviedb.org/3/"
    const val BASE_IMAGE_URL: String = "https://image.tmdb.org/t/p/w342"
}