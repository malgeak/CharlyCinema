package com.malgeakstudios.charlycinema.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.malgeakstudios.charlycinema.data.model.*
import dagger.Provides


@Dao
interface SerieDao {
    @Query("SELECT * FROM tvserie JOIN seriesairingtoday ON tvserie.id = seriesairingtoday.id WHERE seriesairingtoday.page = :miPage")
    fun getSeriePlayingNow(miPage: Int): LiveData<List<TvSeries>>

    @Query("SELECT * FROM tvserie JOIN mostpopularseries ON tvserie.id = mostpopularseries.id WHERE mostpopularseries.page = :miPage")
    fun getMostPopularSeries(miPage: Int): LiveData<List<TvSeries>>

    @Query("SELECT * FROM tvserie WHERE id = :miId")
    fun getSerieDetails(miId: Int): LiveData<TvSeries>

    @Query("SELECT * FROM videosserie WHERE id_serie = :miId")
    fun getSerieVideos(miId: Int): LiveData<List<VideosSerie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSerie(serie: TvSeries)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<TvSeries>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMostPopularList(mostPopularMovies: List<MostPopularSeries>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlayingNowList(playingNow: List<SeriesAiringToday>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertVideos(videos: List<VideosSerie>)
}