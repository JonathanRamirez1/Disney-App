package com.jonathan.disneyapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jonathan.disneyapp.data.model.Login
import com.jonathan.disneyapp.data.repository.LoginRepository
import com.jonathan.disneyapp.utils.NetworkHelper
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _isLogin = MutableLiveData<Boolean>()
    val isLogin: LiveData<Boolean>
        get() = _isLogin

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    private val _connectivity = MutableLiveData<String>()
    val connectivity: LiveData<String>
        get() = _connectivity

    fun onLoginUser(login: Login) {
        viewModelScope.launch {
            _isLoading.postValue(true)
            if (networkHelper.isNetworkConnected()) {
                loginRepository.loginUser(login)
                    .onSuccess {
                        _isLoading.postValue(false)
                        _isLogin.postValue(true)
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