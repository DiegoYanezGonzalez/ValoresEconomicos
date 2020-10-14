package com.example.valoreseconomicos.detalleMoneda.data

import com.example.valoreseconomicos.detalleMoneda.domain.HistorialRepository
import com.example.valoreseconomicos.detalleMoneda.domain.model.Historial

class RemoteHistorialRepository (
    private val repository : ApiHistorialDeValores,
    private val historialMapper : HistorialMapper
) : HistorialRepository {

    override suspend fun obtenerHistorialPorAnio(
        tipoDeMoneda: String, anio: String
    ): Historial {
        val historial = repository.obtenerHistorialDeValores(tipoDeMoneda, anio)
        return historialMapper.mapToEntityHistorial(historial)
    }
}