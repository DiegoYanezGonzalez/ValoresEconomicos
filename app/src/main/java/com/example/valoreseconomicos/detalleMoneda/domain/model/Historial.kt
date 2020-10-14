package com.example.valoreseconomicos.detalleMoneda.domain.model

data class Historial(
    val version : String,
    val autor : String,
    val codigo : String,
    val nombre : String,
    val unidadMedida : String,
    val serie : List<InfoHistorial>
)