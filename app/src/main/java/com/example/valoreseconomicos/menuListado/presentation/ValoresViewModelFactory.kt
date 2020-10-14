package com.example.valoreseconomicos.menuListado.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.valoreseconomicos.menuListado.domain.ObtenerValoresUseCase

class ValoresViewModelFactory (
    private val obtenerApiUseCase: ObtenerValoresUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(ObtenerValoresUseCase::class.java)
            .newInstance(obtenerApiUseCase)
    }
}