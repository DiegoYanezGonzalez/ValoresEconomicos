package com.example.valoreseconomicos.detalleMoneda.domain

import com.example.valoreseconomicos.detalleMoneda.domain.model.Historial

class ObtenerHistorialUseCase (
    private val repository : HistorialRepository
) {
    suspend fun execute(tipoDeMoneda : String, anio : String) : Historial = repository.obtenerHistorialPorAnio(tipoDeMoneda, anio)
}