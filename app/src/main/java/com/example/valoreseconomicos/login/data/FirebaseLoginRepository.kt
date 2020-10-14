package com.example.valoreseconomicos.login.data

import com.example.valoreseconomicos.login.domain.LoginRepository
import com.example.valoreseconomicos.login.domain.model.LoginUsuario
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class FirebaseLoginRepository (
    private val firebaseAuth: FirebaseAuth
) : LoginRepository {

    override suspend fun ingresarUsuario(email: String, contrasena: String): LoginUsuario {
        firebaseAuth
            .signInWithEmailAndPassword(email, contrasena)
            .await()
        val user = firebaseAuth.currentUser
        return LoginUsuario(user?.displayName?: "", user?.email?:"")
    }
}