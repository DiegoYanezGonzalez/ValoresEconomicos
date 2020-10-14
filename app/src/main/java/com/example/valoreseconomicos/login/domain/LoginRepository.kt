package com.example.valoreseconomicos.login.domain

import com.example.valoreseconomicos.login.domain.model.LoginUsuario

interface LoginRepository {
    suspend fun ingresarUsuario(email: String, contrasena: String): LoginUsuario
}