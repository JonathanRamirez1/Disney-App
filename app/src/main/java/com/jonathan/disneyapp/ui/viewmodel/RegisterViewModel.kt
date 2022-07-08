package com.jonathan.disneyapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jonathan.disneyapp.data.model.User
import com.jonathan.disneyapp.data.repository.UserRepository
import com.jonathan.disneyapp.utils.NetworkHelper
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _isRegister = MutableLiveData<Boolean>()
    val isRegister: LiveData<Boolean>
        get() = _isRegister

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    private val _connectivity = MutableLiveData<String>()
    val connectivity: LiveData<String>
        get() = _connectivity

    fun onRegisterUser(users: User) {
        viewModelScope.launch {
            _isLoading.postValue(true)
            if (networkHelper.isNetworkConnected()) {
                userRepository.registerUser(users)
                    .onSuccess {
                        _isLoading.postValue(false)
                        _isRegister.postValue(true)
                    }.onError {
                        _isLoading.postValue(false)
                        _error.postValue(response.errorBody()!!.string())
                    }.onFailure {
                        _isLoading.postValue(false)
                    }
            } else {
                _isLoading.postValue(false)
                _connectivity.postValue("Sin conexión a internet")
            }
        }
    }
}