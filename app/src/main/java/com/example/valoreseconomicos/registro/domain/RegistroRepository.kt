package com.example.valoreseconomicos.registro.domain

import com.example.valoreseconomicos.registro.domain.model.RegistroUsuario

interface RegistroRepository {
    suspend fun registrar(registro: RegistroUsuario): Boolean
}