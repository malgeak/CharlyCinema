package com.malgeakstudios.charlycinema.ui.details.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.recyclerview.widget.RecyclerView
import com.malgeakstudios.charlycinema.data.model.Videos
import com.malgeakstudios.charlycinema.data.model.VideosSerie
import com.malgeakstudios.charlycinema.databinding.VideoCardBinding

class VideoSeriesAdapter(
    private val listener : VideoSeriesAdapter.ItemListener
): RecyclerView.Adapter<VideoSeriesViewHolder>(){
    interface ItemListener {
        fun onItemClicked(movieId: Int)
    }
    private val moviesList = ArrayList<VideosSerie>()

    fun setItems(items: ArrayList<VideosSerie>) {
        this.moviesList.clear()
        this.moviesList.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):VideoSeriesViewHolder {
        val binding: VideoCardBinding =
            VideoCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false)
        return VideoSeriesViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: VideoSeriesViewHolder, position: Int) =
        holder.bind(moviesList[position])

    override fun getItemCount(): Int = moviesList.size

}

class VideoSeriesViewHolder(
    private val itemBinding: VideoCardBinding,
    private val listener: VideoSeriesAdapter.ItemListener)
    : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var movie: VideosSerie

    init {
        itemBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: VideosSerie) {
        this.movie = item
        val webView = itemBinding.videoView
        webView.settings.allowContentAccess = false
        webView.settings.displayZoomControls = false
        val webSource = when (movie.site){
            "YouTube"->{
                "<html><body>Youtube video .. <br> " +
                        "<iframe width=\"350\" height=\"240\" " +
                        "src=\"https://www.youtube.com/embed/${movie.key}\" " +
                        "frameborder=\"0\" allowfullscreen></iframe></body></html>"
            }
            "Vimeo"->{
                "<iframe src=\"https://player.vimeo.com/video/${movie.key}\" width=\"350\"" +
                        " height=\"240\" webkitallowfullscreen mozallowfullscreen allowfullscreen></iframe>"
            }
            else->{
                "<html><body>Youtube video .. <br> " +
                        "<iframe width=\"350\" height=\"240\" " +
                        "src=\"https://www.youtube.com/embed/${movie.key}\" " +
                        "frameborder=\"0\" allowfullscreen></iframe></body></html>"
            }
        }
        webView.settings.javaScriptEnabled = true
        webView.webViewClient =MyWebViewClient()
        webView.loadData(webSource,  "text/html", "utf-8")
    }

    override fun onClick(v: View?) {

    }
}
