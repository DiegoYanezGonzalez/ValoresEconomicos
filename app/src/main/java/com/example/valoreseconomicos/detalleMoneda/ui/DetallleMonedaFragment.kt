package com.example.valoreseconomicos.detalleMoneda.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.valoreseconomicos.R
import com.example.valoreseconomicos.databinding.FragmentDetalleMonedaBinding
import com.example.valoreseconomicos.detalleMoneda.data.HistorialMapper
import com.example.valoreseconomicos.detalleMoneda.data.RemoteHistorialRepository
import com.example.valoreseconomicos.detalleMoneda.domain.ObtenerHistorialUseCase
import com.example.valoreseconomicos.detalleMoneda.domain.model.Historial
import com.example.valoreseconomicos.detalleMoneda.presentation.HistorialState
import com.example.valoreseconomicos.detalleMoneda.presentation.HistorialViewModel
import com.example.valoreseconomicos.detalleMoneda.presentation.HistorialViewModelFactory
import com.example.valoreseconomicos.menuListado.data.RemoteValoresRepository
import com.example.valoreseconomicos.menuListado.data.ValoresMapper
import com.example.valoreseconomicos.menuListado.domain.ObtenerValoresUseCase
import com.example.valoreseconomicos.menuListado.domain.model.DetalleValores
import com.example.valoreseconomicos.menuListado.presentation.ValoresState
import com.example.valoreseconomicos.menuListado.presentation.ValoresViewModel
import com.example.valoreseconomicos.menuListado.presentation.ValoresViewModelFactory
import com.example.valoreseconomicos.network.api.RetrofitHandler
import com.example.valoreseconomicos.util.CODIGO
import com.example.valoreseconomicos.util.extentions.alert
import com.example.valoreseconomicos.util.infoMonedaBundle

class DetalleMonedaFragment : Fragment(R.layout.fragment_detalle_moneda) {

    private lateinit var binding: FragmentDetalleMonedaBinding
    private lateinit var historialAdapter: HistorialAdapter
    private lateinit var historialViewModel: HistorialViewModel
    private lateinit var historialViewModelFactory: HistorialViewModelFactory
    private lateinit var valoresViewModel: ValoresViewModel
    private lateinit var valoresViewModelFactory: ValoresViewModelFactory

    override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBind(view)
        arguments?.let { safeBundle -> cargarMoneda(infoMonedaBundle(safeBundle)) }
        setupDependencies()
        setupLiveData()
        setupRecyclerView()
    }

    private fun setupBind(view : View) {
        binding = FragmentDetalleMonedaBinding.bind(view)
    }

    private fun cargarMoneda(detalleValoresBundle: DetalleValores) {
        binding.apply {
            tvCodigo.text = detalleValoresBundle.codigo
            tvNombre.text = detalleValoresBundle.nombre
            tvUnidadMedida.text = detalleValoresBundle.unidadMedida
            tvValor.text = detalleValoresBundle.valor.toString()
        }
    }

    private fun setupDependencies() {
        historialViewModelFactory = HistorialViewModelFactory(
            ObtenerHistorialUseCase(
                RemoteHistorialRepository(
                    RetrofitHandler.getHistorialApi(),
                    HistorialMapper()
                )
            )
        )

        historialViewModel = ViewModelProvider(this, historialViewModelFactory)
            .get(HistorialViewModel::class.java)

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
        val tipoDeMoneda = arguments?.getString(CODIGO)?: ""
        if (tipoDeMoneda.isNotEmpty()) {
            historialViewModel.getLiveData().observe(
                viewLifecycleOwner,
                { state -> historialHandleState(state) }
            )

            historialViewModel.obtenerHistorial(tipoDeMoneda, binding.etAnio.text.toString())
        }

        valoresViewModel.getLiveData().observe(
            viewLifecycleOwner,
            { state -> valoresHandleState(state) }
        )

        valoresViewModel.obtenerValores()
    }

    private fun historialHandleState(state: HistorialState?) {
        when (state) {
            is HistorialState.LoadingListaHistorial -> mostrarCargandoHistorial()
            is HistorialState.ObtenerHistorialDeValores -> state.resultListadoHistorial?.let { mostrarHistorial(it) }
            is HistorialState.Error -> state.error?.let { mostrarErrorHistorial(it) }
        }
    }

    private fun mostrarCargandoHistorial() {
        alert("Cargando historial.")
    }

    private fun mostrarHistorial(listadoHistorial: Historial) {
        historialAdapter = HistorialAdapter(listadoHistorial.serie)
        binding.rvHistorialValores.adapter = historialAdapter
    }

    private fun mostrarErrorHistorial(error: Throwable) {
        alert("Error: ${error.message}")
    }

    private fun valoresHandleState(state : ValoresState?) {
        when (state) {
            is ValoresState.CargandoListaDeValoresState -> mostrarCargandoValores()
            is ValoresState.ObtenerDetalleDeUnValor -> state.resultDetalleValores?.let { mostrarDetalleValores(it) }
            is ValoresState.Error -> state.error?.let { mostrarErrorValores(it) }
        }
    }

    private fun mostrarCargandoValores() {
        alert("Cargando valores.")
    }

    private fun mostrarDetalleValores(detalle : DetalleValores) {
        binding.apply {
            tvCodigo.text = detalle.codigo
            tvNombre.text = detalle.nombre
            tvUnidadMedida.text = detalle.unidadMedida
            tvValor.text = detalle.valor.toString()
        }
    }

    private fun mostrarErrorValores(error : Throwable) {
        alert("Error: ${error.message}")
    }

    private fun setupRecyclerView() {
        binding.apply {
            rvHistorialValores.setHasFixedSize(true)
            rvHistorialValores.layoutManager = LinearLayoutManager(requireContext())
            rvHistorialValores.addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }
}