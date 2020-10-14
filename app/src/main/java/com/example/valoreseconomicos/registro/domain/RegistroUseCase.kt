package com.example.valoreseconomicos.registro.domain

import com.example.valoreseconomicos.registro.domain.model.RegistroUsuario

class RegistroUseCase (
    private val repository: RegistroRepository
) {
    suspend fun execute(registroUsuario: RegistroUsuario) = repository.registrar(registroUsuario)
}