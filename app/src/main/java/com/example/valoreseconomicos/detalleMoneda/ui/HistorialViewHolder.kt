package com.example.valoreseconomicos.detalleMoneda.ui

import androidx.recyclerview.widget.RecyclerView
import com.example.valoreseconomicos.databinding.ItemHistorialBinding
import com.example.valoreseconomicos.detalleMoneda.domain.model.InfoHistorial

class HistorialViewHolder (
    private val binding: ItemHistorialBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(infoHistorial: InfoHistorial) {
        binding.apply {
            tvFecha.text = infoHistorial.fecha
            tvValorMoneda.text = infoHistorial.valor.toString()
        }
    }

}