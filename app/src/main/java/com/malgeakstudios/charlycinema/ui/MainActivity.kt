package com.malgeakstudios.charlycinema.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.malgeakstudios.charlycinema.R
import com.malgeakstudios.charlycinema.databinding.ActivityMainBinding
import com.malgeakstudios.charlycinema.ui.details.view.DetailsFragment
import com.malgeakstudios.charlycinema.ui.movie.view.MoviesFragment
import com.malgeakstudios.charlycinema.ui.series.view.SeriesFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController: NavController = navHostFragment.navController

        findViewById<BottomNavigationView>(R.id.nav_view)
            .setupWithNavController(navController)

        binding.navView.setOnItemSelectedListener {item ->
            when(item.itemId){
               R.id.navigation_movies->{
                    openFragment(MoviesFragment.newInstance())
                    true
                }
                R.id.navigation_series->{
                    openFragment(SeriesFragment.newInstance())
                    true
                }
                else ->false
            }
        }

        navHostFragment.childFragmentManager.addFragmentOnAttachListener {
                fragmentManager, fragment ->
                when (fragment){
                    is DetailsFragment-> {
                        binding.navView.visibility = View.GONE
                    }
                }
        }
    }


    fun openFragment(fragment: Fragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.nav_host_fragment_activity_main,
            fragment)
        transaction.commit()
    }

    fun showBar(){
        binding.navView.visibility = View.VISIBLE
    }

    override fun onBackPressed() {
        super.onBackPressed()
        showBar()
    }
}