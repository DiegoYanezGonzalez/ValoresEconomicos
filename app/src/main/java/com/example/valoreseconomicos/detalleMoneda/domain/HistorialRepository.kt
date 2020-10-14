package com.example.valoreseconomicos.detalleMoneda.domain

import com.example.valoreseconomicos.detalleMoneda.domain.model.Historial

interface HistorialRepository {
    suspend fun obtenerHistorialPorAnio(tipoDeMoneda : String, anio : String): Historial
}