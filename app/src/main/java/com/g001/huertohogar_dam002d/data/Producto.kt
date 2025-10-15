package com.g001.huertohogar_dam002d.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "productos")
data class Producto(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val nombre: String,
    val descripcion: String,
    val detalle: String,
    val precio: Double,
    val disponible: Boolean = true,
    val stock: Int = 0
)
