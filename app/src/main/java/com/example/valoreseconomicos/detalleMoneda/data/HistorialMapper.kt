package com.example.valoreseconomicos.detalleMoneda.data

import com.example.valoreseconomicos.detalleMoneda.domain.model.Historial
import com.example.valoreseconomicos.detalleMoneda.domain.model.InfoHistorial

class HistorialMapper {
    fun mapToEntityHistorial(historialModel: HistorialModel) : Historial {
        return Historial(
            historialModel.version?: "",
            historialModel.autor?: "",
            historialModel.codigo?: "",
            historialModel.nombre?: "",
            historialModel.unidadMedida?: "",
            mapToEntityInfoHistorial(historialModel.serie?: emptyList())
        )
    }

    fun mapToEntityInfoHistorial(detalleHistorialModel: List<DetalleHistorialModel>) : List<InfoHistorial> {
        return detalleHistorialModel.map {
            mapToEntityInfoHistorial(it)
        }
    }

    fun mapToEntityInfoHistorial(detalleHistorialModel: DetalleHistorialModel) : InfoHistorial {
        return InfoHistorial(
            detalleHistorialModel.fecha?: "",
            detalleHistorialModel.valor?: 0.0f
        )
    }
}