package com.example.valoreseconomicos.registro.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.valoreseconomicos.registro.domain.RegistroUseCase

class RegistroViewModelFactory (
    private val registroUseCase: RegistroUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(RegistroUseCase::class.java)
            .newInstance(registroUseCase)
    }
}