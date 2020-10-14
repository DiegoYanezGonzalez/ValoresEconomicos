package com.example.valoreseconomicos.detalleMoneda.presentation

import com.example.valoreseconomicos.detalleMoneda.domain.model.Historial

open class HistorialState (
    open val resultListadoHistorial : Historial? = null,
    open val error : Throwable? = null
) {
    object LoadingListaHistorial : HistorialState()
    data class ObtenerHistorialDeValores(override  val resultListadoHistorial: Historial?) : HistorialState(resultListadoHistorial = resultListadoHistorial)
    data class Error(override val error : Throwable?) : HistorialState(error = error)
}