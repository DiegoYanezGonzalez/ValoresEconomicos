package com.example.valoreseconomicos.registro.data

import com.example.valoreseconomicos.registro.domain.RegistroRepository
import com.example.valoreseconomicos.registro.domain.model.RegistroUsuario
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

class FirebaseRegistroRepository (
    private val firebaseAuth: FirebaseAuth,
    private val firebaseDataBase: FirebaseDatabase
) : RegistroRepository {

    override suspend fun registrar(registro: RegistroUsuario): Boolean {
        val result = crearUsuarioEnFirebase(registro.email, registro.contrasena)
        guardarNombreEnFirebase(registro.nombre)
        guardarValoresEnBaseDeDatosFirebase(registro.nombre, registro.rut, registro.email)
        return result.user?.email?.isNotEmpty() ?: false
    }

    private suspend fun crearUsuarioEnFirebase(email: String, contrasena: String): AuthResult = firebaseAuth
        .createUserWithEmailAndPassword(email, contrasena)
        .await()

    suspend fun guardarNombreEnFirebase(nombre: String) {
        val user = firebaseAuth.currentUser
        val profileUpdates = UserProfileChangeRequest.Builder()
            .setDisplayName(nombre)
            .build()
        user?.updateProfile(profileUpdates)?.await()
    }

    private suspend fun guardarValoresEnBaseDeDatosFirebase(nombre: String, rut: String, email: String) {
        val dataBase = firebaseDataBase.getReference("usuarios/${rut.replace(".", "")}")
        val registroUsuarioFirebase = RegistroUsuarioFirebase(nombre, rut, email)
        dataBase.setValue(registroUsuarioFirebase).await()
    }
}