package com.jonathan.disneyapp.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.jonathan.disneyapp.R
import com.jonathan.disneyapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        launchMovieFragment(view)
        launchCharacterFragment(view)
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