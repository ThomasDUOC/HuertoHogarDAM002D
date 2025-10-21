package com.g001.huertohogar_dam002d.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HistorialRepository(
    private val pedidoDao: PedidoDao,
    private val carritoDao: CarritoDao
) {

    suspend fun createOrderFromCart() {
        val carritoItems = carritoDao.getAll().first()
        if (carritoItems.isEmpty()) {
            return // Don't create an empty order
        }

        val newPedidoId = pedidoDao.insertPedido(Pedido())
        val pedidoItems = carritoItems.map {
            val producto = pedidoDao.getProductosByIds(listOf(it.productId)).first()
            PedidoItem(
                pedidoId = newPedidoId,
                productId = it.productId,
                quantity = it.quantity,
                price = producto.precio
            )
        }
        pedidoDao.insertPedidoItems(pedidoItems)

        // Clear the cart
        carritoItems.forEach { carritoDao.delete(it) }
    }

    fun getOrderHistory(): Flow<List<PedidoConItems>> {
        return pedidoDao.getPedidosConItems().map { pedidos ->
            pedidos.map { pedido ->
                val pedidoItems = pedidoDao.getPedidoItems(pedido.id)
                val productos = pedidoDao.getProductosByIds(pedidoItems.map { it.productId })
                val itemsConProductos = pedidoItems.map { pedidoItem ->
                    val producto = productos.find { it.id == pedidoItem.productId }!!
                    Pair(pedidoItem, producto)
                }
                PedidoConItems(pedido, itemsConProductos)
            }
        }
    }
}
