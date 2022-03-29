package com.malgeakstudios.charlycinema.ui.movie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.malgeakstudios.charlycinema.data.model.Movie
import com.malgeakstudios.charlycinema.data.model.Videos
import com.malgeakstudios.charlycinema.data.repository.MovieRepository
import com.malgeakstudios.charlycinema.utils.FormattedResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository
): ViewModel() {
    var language: String = "es-Es"
    var pageMostPopular: Int = 1
    var pagePlayingNow: Int = 1
    var movieId:Int = 0

    var movieDetailMutableLiveData : LiveData<FormattedResponse<Movie>> = MutableLiveData()
    var listMostPopularResponseMutableLiveData =
        movieRepository.getMostPopularMovieList(language, pageMostPopular)
    var listPlayingNowResponseMutableLiveData =
        movieRepository.getPlayingNowMovieList(language, pageMostPopular)
    var listVideoMutableLiveData : LiveData<FormattedResponse<List<Videos>>> =
        MutableLiveData()

    fun requestMostPopulareMovies(){
        listMostPopularResponseMutableLiveData =
            movieRepository.getMostPopularMovieList(language, pageMostPopular)
    }

    fun requestPlayingNowMovies(){
        listPlayingNowResponseMutableLiveData =
        movieRepository.getPlayingNowMovieList(language, pagePlayingNow)
    }

    fun requestMovieDetail(){
        movieDetailMutableLiveData =
            movieRepository.getMovie(language, movieId)

        listVideoMutableLiveData =
            movieRepository.getMovieVideos(language, movieId)
    }


}