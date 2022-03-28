package com.malgeakstudios.charlycinema.ui.series.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.malgeakstudios.charlycinema.data.model.TvSeries
import com.malgeakstudios.charlycinema.data.model.VideosSerie
import com.malgeakstudios.charlycinema.data.repository.SerieRepository
import com.malgeakstudios.charlycinema.utils.FormattedResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SerieViewModel @Inject constructor(
    private var serieRepository: SerieRepository
): ViewModel(){
    var language: String = "es-Es"
    var pageMostPopular: Int = 1
    var pagePlayingNow: Int = 1
    var serieId:Int = 0

    var serieDetailMutableLiveData : LiveData<FormattedResponse<TvSeries>> = MutableLiveData()
    var listMostPopularResponseMutableLiveData : LiveData<FormattedResponse<List<TvSeries>>> =
        MutableLiveData()
    var listPlayingNowResponseMutableLiveData : LiveData<FormattedResponse<List<TvSeries>>> =
        MutableLiveData()
    var listVideoMutableLiveData : LiveData<FormattedResponse<List<VideosSerie>>> =
        MutableLiveData()
    /*
    fun requestMostPopulareseries(){
        listMostPopularResponseMutableLiveData =
            serieRepository.getMostPopularSerieList(language, pageMostPopular)
    }

    fun requestPlayingNowseries(){
        listPlayingNowResponseMutableLiveData =
            serieRepository.getPlayingNowSerieList(language, pagePlayingNow)
    }

    fun requestserieVideo(){
        listVideoMutableLiveData =
            serieRepository.getSerieVideos(language, serieId)
    }

    fun requestserieDetail(){
        serieDetailMutableLiveData =
            serieRepository.getSerie(language, serieId)
    }
    */

}