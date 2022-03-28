package com.malgeakstudios.charlycinema.ui.movie.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.malgeakstudios.charlycinema.R
import com.malgeakstudios.charlycinema.databinding.FragmentMoviesBinding
import com.malgeakstudios.charlycinema.ui.details.view.DetailsFragment
import com.malgeakstudios.charlycinema.ui.movie.adapter.MoviesAdapter
import com.malgeakstudios.charlycinema.ui.movie.viewmodel.MovieViewModel
import com.malgeakstudios.charlycinema.utils.APIUtils
import com.malgeakstudios.charlycinema.utils.FormattedResponse
import com.malgeakstudios.charlycinema.utils.RecyclerViewUtils.isLastItemDisplaying
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : Fragment(),  MoviesAdapter.ItemListener{
    private val viewModel: MovieViewModel by viewModels()
    private lateinit var binding: FragmentMoviesBinding
    private lateinit var moviesPlayingNowAdapter : MoviesAdapter
    private lateinit var moviesMostPopularAdapter : MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentMoviesBinding.inflate(
           inflater,
           container,
           false
       )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerViews()
        setObservers()
        initView()
    }

    private fun initView(){
        viewModel.requestPlayingNowMovies()
        viewModel.requestMostPopulareMovies()
    }


    private fun setRecyclerViews(){
        moviesPlayingNowAdapter = MoviesAdapter(this)
        binding.playingNowMoviesRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        binding.playingNowMoviesRecyclerView.adapter = moviesPlayingNowAdapter
        binding.playingNowMoviesRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
            }
        })

        moviesMostPopularAdapter = MoviesAdapter(this)
        binding.mostPopularMoviesRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        binding.mostPopularMoviesRecyclerView.adapter = moviesMostPopularAdapter
        binding.mostPopularMoviesRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (isLastItemDisplaying(recyclerView)) {
                    viewModel.pageMostPopular += 1
                    viewModel.requestMostPopulareMovies()
                }
            }
        })
    }

    private fun setObservers(){
        viewModel.listPlayingNowResponseMutableLiveData.observe(viewLifecycleOwner,{
            when (it.status) {
                FormattedResponse.Status.SUCCESS -> {
                    if (!it.data.isNullOrEmpty())
                        moviesPlayingNowAdapter.setItems(ArrayList(it.data))
                    binding.progressBar.visibility = View.GONE
                }
                FormattedResponse.Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
                FormattedResponse.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        })

        viewModel.listMostPopularResponseMutableLiveData.observe(viewLifecycleOwner,{
            when (it.status) {
                FormattedResponse.Status.SUCCESS -> {
                    if (!it.data.isNullOrEmpty())
                        moviesMostPopularAdapter.setItems(ArrayList(it.data))
                    binding.progressBar.visibility = View.GONE
                }
                FormattedResponse.Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
                FormattedResponse.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        })
    }

    override fun onItemClicked(movieId: Int) {
        findNavController().navigate(
            R.id.action_moviesFragment_to_detailFragment,
            bundleOf("movieId" to movieId, "type" to DetailsFragment.TYPE_MOVIE)
        )
    }
}