package com.example.valoreseconomicos.registro.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.valoreseconomicos.registro.domain.RegistroUseCase
import com.example.valoreseconomicos.registro.domain.model.RegistroUsuario
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import kotlinx.coroutines.launch

class RegistroViewModel (
    private val registroUseCase: RegistroUseCase
) : ViewModel() {

    private val liveData = MutableLiveData<RegistroState>()

    fun getLiveData() = liveData

    fun registrarUsuario(registroUsuario: RegistroUsuario) {
        liveData.postValue(RegistroState.CargandoRegistroState)
        viewModelScope.launch {
            try {
                val result = registroUseCase.execute(registroUsuario)
                handleResult(result)
            } catch (exception: Exception) {
                handleError(exception)
            }
        }
    }

    private fun handleError(exception: Exception) {
        if (exception is FirebaseAuthUserCollisionException) {
            liveData.postValue(RegistroState.EmailYaExisteState)
        } else {
            liveData.postValue(RegistroState.ErrorState(exception))
        }
    }

    private fun handleResult(result: Boolean) {
        if (result) {
            liveData.postValue(RegistroState.RegistroExitosoState)
        } else {
            liveData.postValue(RegistroState.EmailYaExisteState)
        }
    }
}