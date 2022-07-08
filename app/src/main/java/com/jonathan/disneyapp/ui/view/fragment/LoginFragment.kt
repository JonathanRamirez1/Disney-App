package com.jonathan.disneyapp.ui.view.fragment

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import com.jonathan.disneyapp.R
import com.jonathan.disneyapp.data.model.Login
import com.jonathan.disneyapp.databinding.FragmentLoginBinding
import com.jonathan.disneyapp.ui.view.activity.HomeActivity
import com.jonathan.disneyapp.ui.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var navController: NavController
    private lateinit var login: Login

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
        validFields()
        launchRegisterFragment(view)
    }

    private fun launchRegisterFragment(view: View) {
        val register = binding.materialButtonGoRegister

        navController = Navigation.findNavController(view)
        register.setOnClickListener {
            navController.navigate(R.id.registerFragment)
        }
    }

    private fun setObservers() {
        loginViewModel.isLogin.observe(viewLifecycleOwner, { isLogin ->
            if (isLogin) {
                setDestinyView()
            }
        })
        loginViewModel.isLoading.observe(viewLifecycleOwner, { isLoading ->
            if (isLoading) {
                binding.linearLayoutLoading.isVisible = isLoading
            } else {
                binding.linearLayoutLoading.isVisible = false
            }
        })
        loginViewModel.error.observe(viewLifecycleOwner, { error ->
            view?.let { setSnackBar(it, error) }
        })
        loginViewModel.connectivity.observe(viewLifecycleOwner, { connectivity ->
            view?.let { setSnackBar(it, connectivity) }
        })
    }

    private fun setSnackBar(view: View, messageResponse: String) {
        val snackBar = Snackbar.make(view, "", Snackbar.LENGTH_INDEFINITE)
        val customSnackView: View = layoutInflater.inflate(R.layout.custom_snackbar, null)
        val snackBarLayout = snackBar.view as Snackbar.SnackbarLayout
        val buttonOk: Button = customSnackView.findViewById(R.id.ButtonOk)
        val message: TextView = customSnackView.findViewById(R.id.textViewResponse)
        snackBar.view.setBackgroundColor(Color.TRANSPARENT)
        snackBarLayout.setPadding(0, 0, 0, 0)
        message.text = messageResponse
        buttonOk.setOnClickListener {
            snackBar.dismiss()
        }
        snackBarLayout.addView(customSnackView, 0)
        snackBar.show()
    }

    private fun setDestinyView() {
        val intent = Intent(context, HomeActivity::class.java)
        startActivity(intent)
    }

    private fun validFields() {
        binding.materialButtonLogin.setOnClickListener {
            val username: String = binding.textInputEditTextUsername.text.toString()
            val password: String = binding.textInputEditTextPassword.text.toString()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                login = Login(username, password)
                lifecycleScope.launch {
                    loginViewModel.onLoginUser(login)
                }
            } else {
                Toast.makeText(context, "Ningun campo puede estar vacio", Toast.LENGTH_SHORT).show()
            }
        }
    }
}