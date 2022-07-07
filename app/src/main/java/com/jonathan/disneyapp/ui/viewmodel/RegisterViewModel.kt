package com.jonathan.disneyapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jonathan.disneyapp.data.model.User
import com.jonathan.disneyapp.data.repository.UserRepository
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    val isRegister = MutableLiveData<Boolean>()
    val isLoading = MutableLiveData<Boolean>()
    val isError = MutableLiveData<Boolean>()

    fun onRegisterUser(users: User) {
        viewModelScope.launch {
            isLoading.postValue(true)
            userRepository.registerUser(users)
                .onSuccess {
                    isLoading.postValue(false)
                    isRegister.postValue(true)
                }.onError {
                    isLoading.postValue(false)
                    isError.postValue(true)
                }
        }
    }
}