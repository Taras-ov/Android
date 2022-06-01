package com.example.lesson5.ui.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lesson5.data.model.CheckUsernameResponse
import com.example.lesson5.data.model.CheckUsernameResult
import com.example.lesson5.domain.CheckUsernameUseCase
import com.example.lesson5.domain.LoginUseCase
import com.example.lesson5.domain.RegisterUseCase
import com.example.lesson5.domain.SaveTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(

    private val checkUsernameUseCase: CheckUsernameUseCase,
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase,
    private val saveTokenUseCase: SaveTokenUseCase

) : ViewModel(

) {

    val authLiveData = MutableLiveData<CheckUsernameResult>()
    val navLiveData = MutableLiveData<Any>()

    private fun checkUsername(username: String) = viewModelScope.launch {
        authLiveData.value = checkUsernameUseCase.invoke(username)
    }

    private fun login(username: String, password: String) = viewModelScope.launch {
        val loginUseCaseResult = loginUseCase.invoke(username, password)
        saveTokenUseCase.invoke(loginUseCaseResult.token, loginUseCaseResult.userId)
        navLiveData.value = Any()
    }

    private fun register(username: String, password: String) = viewModelScope.launch {
        val registerUseCaseResult = registerUseCase.invoke(username, password)
        saveTokenUseCase.invoke(registerUseCaseResult.token, registerUseCaseResult.userId)
        navLiveData.value = Any()
    }

    fun authLogic(username: String, password: String) {
        when (authLiveData.value) {
            CheckUsernameResult.Free -> register(username, password)
            CheckUsernameResult.Taken -> login(username, password)
            else ->  checkUsername(username)
        }
    }
}

