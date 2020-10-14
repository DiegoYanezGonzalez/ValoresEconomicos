package com.example.valoreseconomicos.menuListado.ui

import androidx.recyclerview.widget.RecyclerView
import com.example.valoreseconomicos.databinding.ItemValoresBinding
import com.example.valoreseconomicos.menuListado.domain.model.DetalleValores

class ValoresViewHolder (
    private val binding : ItemValoresBinding,
    private val listener: ItemListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(moneda : DetalleValores) {
        binding.apply {
            tvNombreMoneda.text = moneda.nombre
            tvValorMoneda.text = moneda.valor.toString()
            cvItemValores.setOnClickListener{ listener.onItemClick(moneda) }
        }
    }
}