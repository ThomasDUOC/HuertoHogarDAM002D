package com.g001.huertohogar_dam002d.data

import kotlinx.coroutines.flow.Flow

class ProductRepository(private val dao: ProductoDao) {

    val productos: Flow<List<Producto>> = dao.getAll()

    suspend fun agregar(nombre: String, descripcion: String, detalle: String, precio: Double, stock: Int) {
        require(nombre.isNotBlank()) { "El nombre no puede estar vacío" }
        require(descripcion.isNotBlank()) { "La descripción no puede estar vacía" }
        require(detalle.isNotBlank()) { "El detalle no puede estar vacío" }
        require(precio >= 0) { "El precio no puede ser negativo" }
        require(stock >= 0) { "El stock no puede ser negativo" }
        dao.insert(Producto(nombre = nombre.trim(), descripcion = descripcion, detalle = detalle, precio = precio, stock = stock))
    }

    suspend fun actualizar(id: Long, nombre: String, descripcion: String, detalle: String, precio: Double, stock: Int) {
        require(id > 0) { "ID invalido" }
        require(nombre.isNotBlank()) { "El nombre no puede estar vacío" }
        require(descripcion.isNotBlank()) { "La descripción no puede estar vacía" }
        require(detalle.isNotBlank()) { "El detalle no puede estar vacío" }
        require(precio >= 0) { "El precio no puede ser negativo" }
        require(stock >= 0) { "El stock no puede ser negativo" }
        dao.update(Producto(id = id, nombre = nombre.trim(), descripcion = descripcion, detalle = detalle, precio = precio, stock = stock))
    }

    suspend fun eliminar(product: Producto) = dao.delete(product)
    suspend fun obtener(id: Long) = dao.findById(id)
}
