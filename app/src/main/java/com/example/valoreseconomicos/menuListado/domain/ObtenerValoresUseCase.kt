package com.example.valoreseconomicos.menuListado.domain

import com.example.valoreseconomicos.menuListado.domain.model.Valores

class ObtenerValoresUseCase (
    private val valoresRepository: ValoresRepository
) {
    suspend fun execute() : Valores = valoresRepository.obtenerValoresApi()
}