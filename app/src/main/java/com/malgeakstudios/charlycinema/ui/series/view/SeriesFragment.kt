package com.malgeakstudios.charlycinema.ui.series.view

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
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.malgeakstudios.charlycinema.R
import com.malgeakstudios.charlycinema.databinding.FragmentMoviesBinding
import com.malgeakstudios.charlycinema.ui.MainActivity
import com.malgeakstudios.charlycinema.ui.details.view.DetailsFragment
import com.malgeakstudios.charlycinema.ui.series.adapter.SeriesAdapter
import com.malgeakstudios.charlycinema.ui.series.viewmodel.SerieViewModel
import com.malgeakstudios.charlycinema.utils.FormattedResponse
import com.malgeakstudios.charlycinema.utils.RecyclerViewUtils.isLastItemDisplaying
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeriesFragment : Fragment(),  SeriesAdapter.ItemListener{
    private val viewModel: SerieViewModel by viewModels()
    private lateinit var binding: FragmentMoviesBinding
    private lateinit var seriesPlayingNowAdapter : SeriesAdapter
    private lateinit var seriesMostPopularAdapter : SeriesAdapter

    companion object{
        fun newInstance(): SeriesFragment {
            val fragment = SeriesFragment()
            return fragment
        }
    }


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
        initView()
        setRecyclerViews()
        setObservers()
    }

    private fun initView(){
        viewModel.requestPlayingNowseries()
        viewModel.requestMostPopulareseries()
    }




    private fun setRecyclerViews(){
        seriesPlayingNowAdapter = SeriesAdapter(this)
        binding.playingNowMoviesRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        binding.playingNowMoviesRecyclerView.adapter = seriesPlayingNowAdapter
        binding.playingNowMoviesRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
            }
        })

        seriesMostPopularAdapter = SeriesAdapter(this)
        binding.mostPopularMoviesRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        binding.mostPopularMoviesRecyclerView.adapter = seriesMostPopularAdapter
        binding.mostPopularMoviesRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (isLastItemDisplaying(recyclerView)) {
                    viewModel.pageMostPopular += 1
                    viewModel.requestMostPopulareseries()
                }
            }
        })
    }

    private fun setObservers(){
        viewModel.listPlayingNowResponseMutableLiveData.observe(viewLifecycleOwner,{
            when (it.status) {
                FormattedResponse.Status.SUCCESS -> {
                    if (!it.data.isNullOrEmpty())
                        seriesPlayingNowAdapter.setItems(ArrayList(it.data))
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
                        seriesMostPopularAdapter.setItems(ArrayList(it.data))
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
            bundleOf("movieId" to movieId, "type" to DetailsFragment.TYPE_SERIE)
        )
    }


}