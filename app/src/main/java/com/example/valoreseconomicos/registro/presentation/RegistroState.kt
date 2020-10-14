package com.example.valoreseconomicos.registro.presentation

sealed class RegistroState(
    open val error: Throwable? = null
) {
    object CargandoRegistroState: RegistroState()
    object RegistroExitosoState : RegistroState()
    object EmailYaExisteState : RegistroState()
    data class ErrorState(override val error: Throwable) : RegistroState(error = error)
}