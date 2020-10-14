package com.example.valoreseconomicos.menuListado.data

import com.example.valoreseconomicos.menuListado.domain.ValoresRepository
import com.example.valoreseconomicos.menuListado.domain.model.Valores

class RemoteValoresRepository (
    private val apiRepository : ApiValores,
    private val valoresMapper : ValoresMapper
) : ValoresRepository {

    override suspend fun obtenerValoresApi(): Valores {
        val valores = apiRepository.obtenerMonedas()
        return valoresMapper.mapToEntityValores(valores)
    }
}