package com.malgeakstudios.charlycinema.ui.details.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.malgeakstudios.charlycinema.R
import com.malgeakstudios.charlycinema.data.model.Movie
import com.malgeakstudios.charlycinema.data.model.TvSeries
import com.malgeakstudios.charlycinema.databinding.FragmentDetailsBinding
import com.malgeakstudios.charlycinema.ui.details.adapters.VideoAdapter
import com.malgeakstudios.charlycinema.ui.details.adapters.VideoSeriesAdapter
import com.malgeakstudios.charlycinema.ui.movie.viewmodel.MovieViewModel
import com.malgeakstudios.charlycinema.ui.series.viewmodel.SerieViewModel
import com.malgeakstudios.charlycinema.utils.FormattedResponse
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment(), VideoAdapter.ItemListener, VideoSeriesAdapter.ItemListener{
    private lateinit var binding: FragmentDetailsBinding
    private val moviesViewModel: MovieViewModel by viewModels()
    private val seriesViewModel: SerieViewModel by viewModels()
    private lateinit var videosAdapter : VideoAdapter
    private lateinit var videosSeriesAdapter : VideoSeriesAdapter
    private var mediaId = 0
    private var mediaTypes = TYPE_MOVIE

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding
            .inflate(
                inflater,
                container,
                false
            )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt("movieId")?.let { mediaId = it }
        arguments?.getInt("type")?.let { mediaTypes = it }

        setListeners()
        getSource()
        setObservers()
        setRecyclerViews()
    }

    private fun setRecyclerViews(){
        when(mediaTypes){
            TYPE_MOVIE->{
                videosAdapter = VideoAdapter(this)
                binding.similarMoviesRecyclerView.layoutManager =
                    LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
                binding.similarMoviesRecyclerView.adapter = videosAdapter
                binding.similarMoviesRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
                })
            }
            TYPE_SERIE->{
                videosSeriesAdapter = VideoSeriesAdapter(this)
                binding.similarMoviesRecyclerView.layoutManager =
                    LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
                binding.similarMoviesRecyclerView.adapter = videosSeriesAdapter
                binding.similarMoviesRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
                })
            }
        }
    }
    private fun getSource(){
        when(mediaTypes){
            TYPE_MOVIE->{
                moviesViewModel.movieId = mediaId
                moviesViewModel.requestMovieDetail()
            }
            TYPE_SERIE->{
                seriesViewModel.serieId = mediaId
                seriesViewModel.requestserieDetail()
            }
        }
    }

    private fun setListeners(){
        binding.backBtn.setOnClickListener {
           requireActivity().onBackPressed()
        }
    }

    private fun setObservers(){
        moviesViewModel.movieDetailMutableLiveData.observe(viewLifecycleOwner,{
            when (it.status) {
                FormattedResponse.Status.SUCCESS -> {
                    if (it.data != null)
                        makeView(it.data)
                    binding.progressBar.visibility = View.GONE
                }
                FormattedResponse.Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    binding.progressBar.visibility = View.GONE
                }
                FormattedResponse.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        })

        moviesViewModel.listVideoMutableLiveData.observe(viewLifecycleOwner,{
            when (it.status) {
                FormattedResponse.Status.SUCCESS -> {
                    if (it.data!!.size>0)
                        videosAdapter.setItems(ArrayList(it.data))
                    else
                        binding.similarMedia.text = "Videos no disponibles"
                    binding.progressBar.visibility = View.GONE
                }
                FormattedResponse.Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    binding.progressBar.visibility = View.GONE
                }
                FormattedResponse.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        })

        seriesViewModel.serieDetailMutableLiveData.observe(viewLifecycleOwner,{
            when (it.status) {
                FormattedResponse.Status.SUCCESS -> {
                    if (it.data != null)
                        makeView(it.data)
                    binding.progressBar.visibility = View.GONE
                }
                FormattedResponse.Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    binding.progressBar.visibility = View.GONE
                }
                FormattedResponse.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        })


        seriesViewModel.listVideoMutableLiveData.observe(viewLifecycleOwner,{
            when (it.status) {
                FormattedResponse.Status.SUCCESS -> {
                    if (it.data!!.size>0)
                        videosSeriesAdapter.setItems(ArrayList(it.data))
                    else
                        binding.similarMedia.text = "Videos no disponibles"
                    binding.progressBar.visibility = View.GONE
                }
                FormattedResponse.Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    binding.progressBar.visibility = View.GONE
                }
                FormattedResponse.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        })

    }

    private fun makeView(data : Movie){
        binding.titleTv.text = data.title.toString()
        binding.detailRank.text = "User Score: ${data.voteAverage}"
        binding.sumaryTv.text = data.overview.toString()
        Glide.with(binding.posterImage)
            .load(data.getImageCaratulaUrl())
            .placeholder(R.drawable.ic_filmstrip_24dp)
            .error(R.drawable.ic_filmstrip_24dp)
            .fitCenter()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(binding.posterImage)
        Glide.with(binding.backIv)
            .load(data.getImageBackUrl())
            .placeholder(R.drawable.ic_filmstrip_24dp)
            .error(R.drawable.ic_filmstrip_24dp)
            .fitCenter()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(binding.backIv)
    }

    private fun makeView(data : TvSeries){
        binding.titleTv.text = data.name
        binding.detailRank.text = "User Score: ${data.voteAverage}"
        binding.sumaryTv.text = data.overview.toString()
        Glide.with(binding.posterImage)
            .load(data.getImageCaratulaUrl())
            .placeholder(R.drawable.ic_filmstrip_24dp)
            .error(R.drawable.ic_filmstrip_24dp)
            .fitCenter()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(binding.posterImage)
        Glide.with(binding.backIv)
            .load(data.getImageBackUrl())
            .placeholder(R.drawable.ic_filmstrip_24dp)
            .error(R.drawable.ic_filmstrip_24dp)
            .fitCenter()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(binding.backIv)
    }
    companion object {
        const val TYPE_MOVIE = 0
        const val TYPE_SERIE = 1
    }

    override fun onItemClicked(movieId: Int) {

    }
}