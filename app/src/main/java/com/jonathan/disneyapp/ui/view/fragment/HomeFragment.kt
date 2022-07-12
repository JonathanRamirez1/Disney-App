package com.jonathan.disneyapp.ui.view.fragment

import android.R.attr
import android.content.Intent.getIntent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.jonathan.disneyapp.R
import com.jonathan.disneyapp.databinding.FragmentHomeBinding
import com.jonathan.disneyapp.ui.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import android.R.attr.defaultValue
import android.content.Intent

import android.content.Intent.getIntent




@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var navController: NavController

    /*private var adminRole : Int = 1
    private var userRole : Int = 2*/
    private var defaultAdminRole  = ""
    private var defaultUserRole  = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
        launchMovieFragment(view)
        launchCharacterFragment(view)
    }

    private fun setObservers() {
        defaultAdminRole = arguments?.getString("roleAdmin").toString()
        defaultUserRole = arguments?.getString("roleUser").toString()

        Log.e("HOME", "ADMIN $defaultAdminRole")
        Log.e("HOME", "USER $defaultUserRole")

      //  binding.materialFloatingActionButtonAdmin.isVisible = admin != 1

        /*if (adminRole == defaultAdminRole && userRole == defaultUserRole) {
            binding.materialFloatingActionButtonAdmin.isVisible = true
        } else if (userRole == defaultUserRole) {
            binding.materialFloatingActionButtonAdmin.isVisible = true
        }*/
    }

    private fun launchMovieFragment(view: View) {
        val movie = binding.cardViewMovies

        navController = Navigation.findNavController(view)
        movie.setOnClickListener {
            navController.navigate(R.id.movieFragment)
        }
    }

    private fun launchCharacterFragment(view: View) {
        val character = binding.cardViewCharacters

        navController = Navigation.findNavController(view)
        character.setOnClickListener {
            navController.navigate(R.id.characterFragment)
        }
    }
}