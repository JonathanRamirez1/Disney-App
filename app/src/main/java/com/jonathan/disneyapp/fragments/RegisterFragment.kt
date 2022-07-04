package com.jonathan.disneyapp.fragments

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.checkbox.MaterialCheckBox
import com.jonathan.disneyapp.R
import com.jonathan.disneyapp.databinding.FragmentRegisterBinding
import com.jonathan.disneyapp.models.UserDTO
import com.jonathan.disneyapp.services.ApiService
import com.skydoves.sandwich.*
import com.skydoves.sandwich.adapters.ApiResponseCallAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var navController: NavController
    private lateinit var userDTO: UserDTO

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        goBackLogin(view)
        validFields()
    }

    private fun launchLoginFragment(view: View) {
        navController = Navigation.findNavController(view)
        navController.navigate(R.id.loginFragment)
    }

    private fun goBackLogin(view: View) {
        val backLogin = binding.materialButtonGoBackLogin

        navController = Navigation.findNavController(view)
        backLogin.setOnClickListener {
            navController.navigate(R.id.loginFragment)
        }
    }

    private fun validFields() {
        binding.materialButtonRegister.setOnClickListener {
            val username: String = binding.textInputEditTextUsername.text.toString()
            val email: String = binding.textInputEditTextEmail.text.toString()
            val password: String = binding.textInputEditTextPassword.text.toString()
            val userRole: MaterialCheckBox = binding.materialCheckBoxUser
            val adminRole: MaterialCheckBox = binding.materialCheckBoxAdmin

            if (username.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                if (username.length >= 3) {
                    if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        if (password.length >= 6) {
                            if (userRole.isChecked || adminRole.isChecked) {
                                userDTO = UserDTO(username, email, password, setupRoles(userRole, adminRole))
                                lifecycleScope.launch {
                                    handlerResponse()
                                }
                            } else {
                                Toast.makeText(context, "Selecciona por lo menos un Role", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            Toast.makeText(context, "Minimo 6 caracteres para la contraseña", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        binding.textInputEditTextEmail.error = "Email mal"
                        binding.textInputEditTextEmail.requestFocus()
                    }
                } else {
                    Toast.makeText(context, "Minimo 3 caracteres para el username", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, "Ningun campo puede estar vacio", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupRoles(userRole: MaterialCheckBox, adminRole: MaterialCheckBox): ArrayList<String> {
        val roles: ArrayList<String> = ArrayList()
        if (userRole.isChecked && adminRole.isChecked) {
            roles.add(userRole.text.toString().lowercase())
            roles.add(adminRole.text.toString().lowercase())
        } else if (userRole.isChecked) {
            roles.add(userRole.text.toString().lowercase())
        } else if (adminRole.isChecked) {
            roles.add(adminRole.text.toString().lowercase())
        }
        return roles
    }

    private fun getClientHttp(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    //TODO CAMBIAR IP
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://192.168.101.8:8080/")
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
            .client(getClientHttp())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private suspend fun onRegisterUser(users: UserDTO): ApiResponse<UserDTO> = withContext(Dispatchers.IO) {
        val api = getRetrofit().create(ApiService::class.java)
        api.registerUser(users)
    }

    private suspend fun handlerResponse() {
        onRegisterUser(userDTO)
            .onSuccess {
                Toast.makeText(context, "REGISTRO EXITOSO", Toast.LENGTH_SHORT).show()
                view?.let { launchLoginFragment(it) }
            }.onError {
                Toast.makeText(context, "ALGO ESTA MAL!!", Toast.LENGTH_SHORT).show()
            }
    }
}