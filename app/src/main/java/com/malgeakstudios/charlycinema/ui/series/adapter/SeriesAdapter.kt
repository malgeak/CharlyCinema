package com.malgeakstudios.charlycinema.ui.series.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.github.florent37.glidepalette.BitmapPalette
import com.github.florent37.glidepalette.GlidePalette
import com.malgeakstudios.charlycinema.R
import com.malgeakstudios.charlycinema.data.model.Movie
import com.malgeakstudios.charlycinema.data.model.TvSeries
import com.malgeakstudios.charlycinema.databinding.MovieCardBinding

class SeriesAdapter(
    private val listener : ItemListener
): RecyclerView.Adapter<SeriesViewHolder>(){
    interface ItemListener {
        fun onItemClicked(seriesId: Int)
    }
    private val moviesList = ArrayList<TvSeries>()

    fun setItems(items: ArrayList<TvSeries>) {
        this.moviesList.clear()
        this.moviesList.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
        val binding: MovieCardBinding =
            MovieCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false)
           return SeriesViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) =
        holder.bind(moviesList[position])

    override fun getItemCount(): Int = moviesList.size
}

class SeriesViewHolder(
    private val itemBinding: MovieCardBinding,
    private val listener: SeriesAdapter.ItemListener)
    : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var movie: TvSeries

    init {
        itemBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: TvSeries) {
        this.movie = item

        itemBinding.movieTitle.text = item.name

        Glide.with(itemBinding.root)
            .load(item.getImageCaratulaUrl())
            .listener(
                GlidePalette.with(item.getImageCaratulaUrl())
                .use(BitmapPalette.Profile.VIBRANT)
                .intoBackground(itemBinding.movieTittleBackground)
                .crossfade(true))
            .placeholder(R.drawable.ic_filmstrip_24dp)
            .error(R.drawable.ic_filmstrip_24dp)
            .fitCenter()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(itemBinding.movieImage)
    }

    override fun onClick(v: View?) {
        listener.onItemClicked(seriesId = movie.id)
    }
}


