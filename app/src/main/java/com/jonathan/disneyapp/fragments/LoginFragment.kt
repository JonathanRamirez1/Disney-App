package com.jonathan.disneyapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.jonathan.disneyapp.R
import com.jonathan.disneyapp.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        launchRegisterFragment(view)
    }

    private fun launchRegisterFragment(view: View) {
        val register = binding.materialButtonGoRegister

        navController = Navigation.findNavController(view)
        register.setOnClickListener {
            navController.navigate(R.id.registerFragment)
        }
    }
}