package com.example.valoreseconomicos.menuListado.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.valoreseconomicos.R
import com.example.valoreseconomicos.databinding.FragmentMenuBinding
import com.example.valoreseconomicos.menuListado.data.RemoteValoresRepository
import com.example.valoreseconomicos.menuListado.data.ValoresMapper
import com.example.valoreseconomicos.menuListado.domain.ObtenerValoresUseCase
import com.example.valoreseconomicos.menuListado.domain.model.DetalleValores
import com.example.valoreseconomicos.menuListado.domain.model.Valores
import com.example.valoreseconomicos.menuListado.presentation.ValoresState
import com.example.valoreseconomicos.menuListado.presentation.ValoresViewModel
import com.example.valoreseconomicos.menuListado.presentation.ValoresViewModelFactory
import com.example.valoreseconomicos.network.api.RetrofitHandler
import com.example.valoreseconomicos.util.extentions.alert
import com.example.valoreseconomicos.util.infoMonedaBundle

class MenuFragment : Fragment(R.layout.fragment_menu) {

    private lateinit var binding: FragmentMenuBinding
    private lateinit var valoresAdapter: ValoresAdapter
    private lateinit var valoresViewModel: ValoresViewModel
    private lateinit var valoresViewModelFactory: ValoresViewModelFactory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBind(view)
        setupDependencies()
        setupLiveData()
        setupRecyclerView()
        obtenerViewModel()
    }

    private fun setupBind(view: View) {
        binding = FragmentMenuBinding.bind(view)
    }

    private fun setupDependencies() {
        valoresViewModelFactory = ValoresViewModelFactory(
            ObtenerValoresUseCase(
                RemoteValoresRepository(
                    RetrofitHandler.getValoresApi(),
                    ValoresMapper()
                )
            )
        )

        valoresViewModel = ViewModelProvider(this, valoresViewModelFactory)
            .get(ValoresViewModel::class.java)
    }

    private fun setupLiveData() {
        valoresViewModel.getLiveData().observe(
            viewLifecycleOwner,
            { state -> handleState(state) }
        )
        valoresViewModel.obtenerValores()
    }

    private fun handleState(state: ValoresState?) {
        when (state) {
            is ValoresState.CargandoListaDeValoresState -> mostrarCargando()
            is ValoresState.ObtenerTodosLosValores -> state.resultValores?.let { mostrarValores(it) }
            is ValoresState.Error -> state.error?.let { mostrarError(it) }
        }
    }

    private fun mostrarCargando() {
        alert("Cargando valores.")
    }

    private fun mostrarValores(valores: Valores) {
        valoresAdapter = ValoresAdapter(valores.listadoDeMonedas, object : ItemListener {
            override fun onItemClick(detalleValores: DetalleValores) {
                view?.let { safeView ->
                    Navigation.findNavController(safeView)
                        .navigate(
                            R.id.action_menuFragment_to_detalleMonedaFragment,
                            infoMonedaBundle(detalleValores)
                        )
                }
            }
        })
        binding.rvListaValores.adapter = valoresAdapter
    }

    private fun mostrarError(error: Throwable) {
        alert("ErrorState: ${error.message}")
    }

    private fun setupRecyclerView() {
        binding.apply {
            rvListaValores.setHasFixedSize(true)
            rvListaValores.layoutManager = LinearLayoutManager(requireContext())
            rvListaValores.itemAnimator = DefaultItemAnimator()
        }
    }

    private fun obtenerViewModel() {
        valoresViewModel.obtenerValores()
    }
}