package com.example.valoreseconomicos.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.valoreseconomicos.login.domain.LoginUseCase

class LoginViewModelFactory (
    private val loginUseCase: LoginUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(LoginUseCase::class.java)
            .newInstance(loginUseCase)
    }
}