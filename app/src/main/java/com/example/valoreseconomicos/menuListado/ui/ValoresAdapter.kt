package com.example.valoreseconomicos.menuListado.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.valoreseconomicos.databinding.ItemValoresBinding
import com.example.valoreseconomicos.menuListado.domain.model.DetalleValores

class ValoresAdapter (
    private val valores: List<DetalleValores>,
    private val listener : ItemListener
) : RecyclerView.Adapter<ValoresViewHolder>() {

    private lateinit var binding: ItemValoresBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ValoresViewHolder {
        binding = ItemValoresBinding.inflate(LayoutInflater.from(parent.context), parent , false)
        return ValoresViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: ValoresViewHolder, position: Int) {
        holder.bind(valores[position])
    }

    override fun getItemCount(): Int {
        return valores.size
    }

}