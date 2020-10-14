package com.example.valoreseconomicos.menuListado.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.valoreseconomicos.menuListado.domain.ObtenerValoresUseCase
import com.example.valoreseconomicos.menuListado.domain.model.Valores
import kotlinx.coroutines.launch
import java.lang.Exception

class ValoresViewModel(
    private val obtenerValoresUseCase: ObtenerValoresUseCase
) : ViewModel() {

    private val liveData = MutableLiveData<ValoresState>()

    fun getLiveData() = liveData

    fun obtenerValores() {
        liveData.postValue(ValoresState.CargandoListaDeValoresState)
        viewModelScope.launch {
            try {
                val result = obtenerValoresUseCase.execute()
                handleResult(result)
            } catch (exception: Exception) {
                handleError(exception)
            }
        }
    }

    private fun handleResult(result: Valores) {
        liveData.postValue(ValoresState.ObtenerTodosLosValores(result))
    }

    private fun handleError(exception: Exception) {
        liveData.postValue(ValoresState.Error(exception))
    }
}