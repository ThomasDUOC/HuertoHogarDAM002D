package com.g001.huertohogar_dam002d.carrito

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

sealed class EstadoCarrito {
    data object Idle : EstadoCarrito()
    data class Actualizado(val total: Double) : EstadoCarrito()
    data class Confirmado(val total: Double) : EstadoCarrito()
}

class CarritoViewModel : ViewModel() {
    private val carrito = CarritoCompra()

    private val _items = MutableStateFlow<List<CarritoItem>>(emptyList())
    val items: StateFlow<List<CarritoItem>> = _items.asStateFlow()

    private val _estado = MutableStateFlow<EstadoCarrito>(EstadoCarrito.Idle)
    val estado: StateFlow<EstadoCarrito> = _estado.asStateFlow()

    fun agregar(item: CarritoItem) {
        carrito.agregar(item)
        sincronizar()
    }

    fun eliminar(productoId: String) {
        carrito.eliminar(productoId)
        sincronizar()
    }

    fun confirmar() {
        val total = carrito.total()
        carrito.vaciar()
        _items.value = emptyList()
        _estado.value = EstadoCarrito.Confirmado(total)
    }

    private fun sincronizar() {
        val lista = carrito.obtenerItems()
        _items.value = lista
        _estado.value = EstadoCarrito.Actualizado(lista.sumOf { it.subtotal })
    }
}
