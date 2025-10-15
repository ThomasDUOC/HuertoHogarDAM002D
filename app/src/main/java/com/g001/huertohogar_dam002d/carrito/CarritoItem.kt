package com.g001.huertohogar_dam002d.carrito

data class CarritoItem(
    val productoId: String,
    val nombre: String,
    val precioUnitario: Double,
    var cantidad: Int
) {
    val subtotal: Double get() = precioUnitario * cantidad
}
