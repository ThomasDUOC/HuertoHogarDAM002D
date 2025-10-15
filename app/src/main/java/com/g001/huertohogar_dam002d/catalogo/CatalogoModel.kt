package com.g001.huertohogar_dam002d.catalogo

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CatalogoViewModel : ViewModel() {
    private val _productos = MutableStateFlow<List<Producto>>(emptyList())
    val productos: StateFlow<List<Producto>> = _productos.asStateFlow()

    private val _servicios = MutableStateFlow<List<Servicio>>(emptyList())
    val servicios: StateFlow<List<Servicio>> = _servicios.asStateFlow()

    fun cargarDemo() {
        _productos.value = listOf(
            Producto(id = "p1", nombre = "Silla Eames", descripcion = "Clásica", precio = 39990.0, disponible = true, stock = 5),
            Producto(id = "p2", nombre = "Mesa Roble", descripcion = "160x90", precio = 129990.0, disponible = true, stock = 2)
        )
        _servicios.value = listOf(
            Servicio(id = "s1", nombre = "Arriendo de herramientas", descripcion = "Por día", precio = 9990.0),
            Servicio(id = "s2", nombre = "Instalación a domicilio", descripcion = "Zona RM", precio = 19990.0)
        )
    }

    fun filtrarProductos(query: String) {
        val base = _productos.value
        _productos.value = if (query.isBlank()) base else base.filter { it.nombre.contains(query, true) }
    }
}
