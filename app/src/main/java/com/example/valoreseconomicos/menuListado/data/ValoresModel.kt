package com.example.valoreseconomicos.menuListado.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ValoresModel(
    val version: String,
    val autor: String,
    val fecha: String,
    val uf: DetalleValoresModel,
    val ivp: DetalleValoresModel,
    val dolar: DetalleValoresModel,
    @Json(name = "dolar_intercambio")
    val dolarIntercambio: DetalleValoresModel,
    val euro: DetalleValoresModel,
    val ipc: DetalleValoresModel,
    val utm: DetalleValoresModel,
    val imacec: DetalleValoresModel,
    val tpm: DetalleValoresModel,
    @Json(name = "libra_cobre")
    val libraCobre: DetalleValoresModel,
    @Json(name = "tasa_desempleo")
    val tasaDesempleo: DetalleValoresModel,
    val bitcoin: DetalleValoresModel
)