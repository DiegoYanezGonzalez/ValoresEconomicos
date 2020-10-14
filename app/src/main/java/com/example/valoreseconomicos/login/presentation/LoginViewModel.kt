package com.example.valoreseconomicos.login.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.valoreseconomicos.login.domain.LoginUseCase
import com.example.valoreseconomicos.login.domain.model.LoginUsuario
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import kotlinx.coroutines.launch

class LoginViewModel (
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val liveData = MutableLiveData<LoginState>()

    fun getLiveData() = liveData

    fun ingresarUsuario(email: String, contrasena: String) {
        liveData.postValue(LoginState.CargandoLoginState)
        viewModelScope.launch {
            try {
                val result = loginUseCase.execute(email, contrasena)
                handleResult(result)
            } catch (error: Exception) {
                handleError(error)
            }
        }
    }

    private fun handleResult(result: LoginUsuario) {
        liveData.postValue(LoginState.LoginExitosoState(result))
    }

    private fun handleError(error: Exception) {
        if (error is FirebaseAuthInvalidCredentialsException) {
            liveData.postValue(LoginState.UsuarioInvalidoState)
        } else {
            liveData.postValue(LoginState.ErrorState(error))
        }
    }
}