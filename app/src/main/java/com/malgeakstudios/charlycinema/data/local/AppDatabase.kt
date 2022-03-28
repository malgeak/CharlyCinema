package com.malgeakstudios.charlycinema.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.aherrera.tmdb.utils.converters.IntArrayListConverter
import com.aherrera.tmdb.utils.converters.StringArrayListConverter
import com.malgeakstudios.charlycinema.data.local.dao.MovieDao
import com.malgeakstudios.charlycinema.data.local.dao.SerieDao
import com.malgeakstudios.charlycinema.data.model.*
import com.malgeakstudios.charlycinema.utils.converters.*

@Database(
    entities = [Creator::class, Genre::class, MostPopularMovies::class, MostPopularSeries::class,
        Movie::class, MoviesPlayingNow::class, ProductionCompany::class, Season::class,
        SeriesAiringToday::class, SpokenLanguage::class, TvSeries::class, Videos::class,
        VideosSerie::class],
    version = 5,
    exportSchema = false
)
@TypeConverters(value = [(IntArrayListConverter::class),
    (StringArrayListConverter::class),
    (ObjectListConverter::class),
    (SpokenLanguageListConverter::class),
    (ProductCompaniesListConverter::class),
    (CreatorsListConverter::class),
    (SeasonListConverter::class)])
abstract class AppDatabase: RoomDatabase() {

    abstract fun movieDao():MovieDao
    abstract fun serieDao():SerieDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(
                    context
                    //mDataBase
                ).also { instance = it }
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, DatabaseEnum.movies.name)
                .fallbackToDestructiveMigration()
                .build()
    }

    enum class DatabaseEnum {
        movies, tvseries
    }
}