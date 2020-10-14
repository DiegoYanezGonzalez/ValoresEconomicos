package com.example.valoreseconomicos.registro.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.valoreseconomicos.R
import com.example.valoreseconomicos.databinding.FragmentRegisterBinding
import com.example.valoreseconomicos.registro.data.FirebaseRegistroRepository
import com.example.valoreseconomicos.registro.domain.RegistroUseCase
import com.example.valoreseconomicos.registro.domain.model.RegistroUsuario
import com.example.valoreseconomicos.registro.presentation.RegistroState
import com.example.valoreseconomicos.registro.presentation.RegistroViewModel
import com.example.valoreseconomicos.registro.presentation.RegistroViewModelFactory
import com.example.valoreseconomicos.util.extentions.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RegisterFragment : Fragment(R.layout.fragment_register) {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var registroViewModel: RegistroViewModel
    private lateinit var registroViewModelFactory: RegistroViewModelFactory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDependencies()
        setupLiveData()
        setupBind(view)
        setupListeners()
    }

    private fun setupDependencies() {
        registroViewModelFactory = RegistroViewModelFactory(
            RegistroUseCase(
                FirebaseRegistroRepository(
                    FirebaseAuth.getInstance(),
                    FirebaseDatabase.getInstance()
                )
            )
        )

        registroViewModel = ViewModelProvider(
            this,
            registroViewModelFactory
        ).get(RegistroViewModel::class.java)
    }

    private fun setupLiveData() {
        registroViewModel.getLiveData()
            .observe(viewLifecycleOwner,
                { state -> state?.let { handleState(it) } }
            )
    }

    private fun handleState(state: RegistroState) {
        when (state) {
            is RegistroState.CargandoRegistroState -> showLoading()
            is RegistroState.ErrorState -> showError()
            is RegistroState.EmailYaExisteState -> repeatedEmail()
            is RegistroState.RegistroExitosoState -> successRegister()
        }
    }

    private fun showLoading() {
        alert("Cargando...")
    }

    private fun showError() {
        alert("ErrorState del servidor.")
    }

    private fun repeatedEmail() {
        alert("El email ya esta siendo usado.")
    }

    private fun successRegister() {
        alert("Registro exitoso.")
    }

    private fun setupBind(view: View) {
        binding = FragmentRegisterBinding.bind(view)
    }

    private fun setupListeners() {
        binding.apply {
            btnRegistrar.setOnClickListener {
                if (validarValoresDelEditText()) {
                    registroViewModel.registrarUsuario(obtenerValoresDelEditText())
                }
            }

            btnVolver.setOnClickListener {
                activity?.onBackPressed()
            }
        }
    }

    private fun validarValoresDelEditText(): Boolean {
        binding.apply {
            return etConfirmarContrasena.isValidConfirmPassInput(
                "Las contraseñas deben coincidir",
                etContrasena.text.toString()) ||
                    etContrasena.isValidPassInput("Ingrese una contraseña valida") ||
                    etEmail.isValidEmailInput("Ingrese un correo electrónico valido") ||
                    etRut.isValidRutInput("Ingrese un RUN valido") ||
                    etNombre.isValidNameInput("Ingrese un nombre valido")
        }
    }

    private fun obtenerValoresDelEditText(): RegistroUsuario {
        binding.apply {
            return RegistroUsuario(
                etNombre.text.toString(),
                etRut.text.toString(),
                etEmail.text.toString(),
                etContrasena.text.toString()
            )
        }
    }
}