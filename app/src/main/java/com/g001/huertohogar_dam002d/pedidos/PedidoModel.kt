package com.g001.huertohogar_dam002d.pedidos

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PedidoViewModel : ViewModel() {
    private val _historial = MutableStateFlow<List<Pedido>>(emptyList())
    val historial: StateFlow<List<Pedido>> = _historial.asStateFlow()

    fun registrarPedido(pedido: Pedido) {
        _historial.value = _historial.value + pedido
    }

    fun cargarDemo(clienteId: String) {
        _historial.value = listOf(
            Pedido(id = "o1", clienteId = clienteId, items = listOf("p1" to 2), fecha = System.currentTimeMillis(), estado = EstadoPedido.COMPLETADO),
            Pedido(id = "o2", clienteId = clienteId, items = listOf("p2" to 1), fecha = System.currentTimeMillis(), estado = EstadoPedido.CANCELADO)
        )
    }
}
