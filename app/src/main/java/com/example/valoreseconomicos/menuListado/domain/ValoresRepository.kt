package com.example.valoreseconomicos.menuListado.domain

import com.example.valoreseconomicos.menuListado.domain.model.Valores

interface ValoresRepository {
    suspend fun obtenerValoresApi(): Valores
}