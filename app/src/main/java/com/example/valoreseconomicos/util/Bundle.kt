package com.example.valoreseconomicos.util

import android.os.Bundle
import com.example.valoreseconomicos.menuListado.domain.model.DetalleValores

const val CODIGO = "codigo"
const val NOMBRE = "nombre"
const val UNIDAD_DE_MEDIDA = "unidad de medida"
const val FECHA = "fecha"
const val VALOR = "valor"

fun infoMonedaBundle(detalleValores: DetalleValores): Bundle {
    val bundle = Bundle()
    bundle.putString(CODIGO, detalleValores.codigo)
    bundle.putString(NOMBRE, detalleValores.nombre)
    bundle.putString(UNIDAD_DE_MEDIDA, detalleValores.unidadMedida)
    bundle.putString(FECHA, detalleValores.fecha)
    bundle.putFloat(VALOR, detalleValores.valor ?: 0.0f)
    return bundle
}

fun infoMonedaBundle(bundle: Bundle): DetalleValores {
    val codigo = bundle.getString(CODIGO)
    val nombre = bundle.getString(NOMBRE)
    val unidadDeMedida = bundle.getString(UNIDAD_DE_MEDIDA)
    val fecha = bundle.getString(FECHA)
    val valor = bundle.getFloat(VALOR)
    return DetalleValores(codigo, nombre, unidadDeMedida, fecha, valor)
}
