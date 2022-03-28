package com.malgeakstudios.charlycinema.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.malgeakstudios.charlycinema.data.model.MostPopularMovies
import com.malgeakstudios.charlycinema.data.model.Movie
import com.malgeakstudios.charlycinema.data.model.MoviesPlayingNow
import com.malgeakstudios.charlycinema.data.model.Videos

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie JOIN moviesplayingnow ON movie.id = moviesplayingnow.id WHERE moviesplayingnow.page = :miPage")
    fun getMoviesPlayingNow(miPage: Int): LiveData<List<Movie>>

    @Query("SELECT * FROM movie JOIN mostpopularmovies ON movie.id = mostpopularmovies.id WHERE mostpopularmovies.page = :miPage")
    fun getMostPopularMovies(miPage: Int): LiveData<List<Movie>>

    @Query("SELECT * FROM movie WHERE id = :miId")
    fun getMovieDetails(miId: Int): LiveData<Movie>

    @Query("SELECT * FROM videos WHERE id_movie = :miId")
    fun getMovieVideos(miId: Int): LiveData<List<Videos>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movies: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<Movie>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMostPopularList(mostPopularMovies: List<MostPopularMovies>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlayingNowList(playingNow: List<MoviesPlayingNow>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertVideos(videos: List<Videos>)
}