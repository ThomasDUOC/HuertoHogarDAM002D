package com.g001.huertohogar_dam002d.inventario

data class StockItem(
    val productoId: String,
    val nombre: String,
    var stock: Int,
    val minimo: Int = 0,
    val disponible: Boolean = true
)
