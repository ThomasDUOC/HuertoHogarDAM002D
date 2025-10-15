package com.g001.huertohogar_dam002d.pedidos

data class Pedido(
    val id: String,
    val clienteId: String,
    val items: List<Pair<String, Int>>, // productoId + cantidad
    val fecha: Long,
    val estado: EstadoPedido
)
