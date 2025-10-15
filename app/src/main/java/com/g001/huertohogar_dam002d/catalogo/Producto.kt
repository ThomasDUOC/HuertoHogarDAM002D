package com.g001.huertohogar_dam002d.catalogo

data class Producto(
    val id: String,
    val nombre: String,
    val descripcion: String,
    val precio: Double,
    val disponible: Boolean = true,
    val stock: Int = 0
)
