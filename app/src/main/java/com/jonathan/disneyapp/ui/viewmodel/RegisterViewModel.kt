package com.jonathan.disneyapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jonathan.disneyapp.data.model.User
import com.jonathan.disneyapp.data.repository.UserRepository
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val userRepository: UserRepository, ) : ViewModel() {

    val isRegister = MutableLiveData<Boolean>()
    val isError = MutableLiveData<Boolean>()

    suspend fun onRegisterUser(users: User) {
        viewModelScope.launch {
            userRepository.registerUser(users)
                .suspendOnSuccess {
                    isRegister.postValue(true)
                }.suspendOnError {
                    isError.postValue(true)
                }
        }
    }
}