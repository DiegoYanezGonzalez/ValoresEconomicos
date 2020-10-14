package com.example.valoreseconomicos.menuListado.data

import retrofit2.http.GET

interface ApiValores {
    @GET("api")
    suspend fun obtenerMonedas() : ValoresModel
}