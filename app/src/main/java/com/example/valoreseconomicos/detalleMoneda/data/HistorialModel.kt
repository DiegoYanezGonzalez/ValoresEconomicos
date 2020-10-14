package com.example.valoreseconomicos.detalleMoneda.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HistorialModel(
    val version : String? = null,
    val autor : String? = null,
    val codigo : String? = null,
    val nombre : String? = null,
    @Json(name = "unidad_medida")
    val unidadMedida : String? = null,
    val serie : List<DetalleHistorialModel>? = null
)