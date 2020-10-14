package com.example.valoreseconomicos.menuListado.ui

import com.example.valoreseconomicos.menuListado.domain.model.DetalleValores

interface ItemListener {
    fun onItemClick(detalleValores : DetalleValores)
}