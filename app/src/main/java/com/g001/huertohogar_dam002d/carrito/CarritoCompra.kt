package com.g001.huertohogar_dam002d.carrito

class CarritoCompra {
    private val items = mutableListOf<CarritoItem>()

    fun agregar(item: CarritoItem) {
        val existente = items.find { it.productoId == item.productoId }
        if (existente != null) {
            existente.cantidad += item.cantidad
        } else {
            items.add(item)
        }
    }

    fun eliminar(productoId: String) {
        items.removeIf { it.productoId == productoId }
    }

    fun vaciar() {
        items.clear()
    }

    fun total(): Double = items.sumOf { it.subtotal }

    fun obtenerItems(): List<CarritoItem> = items.toList()
}
