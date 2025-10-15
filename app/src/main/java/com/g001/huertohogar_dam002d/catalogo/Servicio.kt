package com.g001.huertohogar_dam002d.catalogo

data class Servicio(
    val id: String,
    val nombre: String,
    val descripcion: String,
    val precio: Double,
    val disponible: Boolean = true
)
