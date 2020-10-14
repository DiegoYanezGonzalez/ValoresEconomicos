package com.example.valoreseconomicos.login.domain

class LoginUseCase (
    private val loginRepository: LoginRepository
) {
    suspend fun execute(email: String, contrasena: String) = loginRepository.ingresarUsuario(email, contrasena)
}