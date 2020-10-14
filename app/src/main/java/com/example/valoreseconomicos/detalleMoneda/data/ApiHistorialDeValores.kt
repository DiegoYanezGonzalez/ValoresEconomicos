package com.example.valoreseconomicos.detalleMoneda.data

import retrofit2.http.GET
import retrofit2.http.Path

interface ApiHistorialDeValores {
    @GET("api/{tipo_indicador}/{yyyy}")
    suspend fun obtenerHistorialDeValores(
        @Path("tipo_indicador") tipoIndicador: String,
        @Path("yyyy") anio: String
    ) : HistorialModel
}