package com.g001.huertohogar_dam002d.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

data class PedidoConItems(
    val pedido: Pedido,
    val items: List<Pair<PedidoItem, Producto>>
)

@Dao
interface PedidoDao {
    @Insert
    suspend fun insertPedido(pedido: Pedido): Long

    @Insert
    suspend fun insertPedidoItems(items: List<PedidoItem>)

    @Transaction
    @Query("SELECT * FROM pedidos ORDER BY timestamp DESC")
    fun getPedidosConItems(): Flow<List<Pedido>>

    @Transaction
    @Query("SELECT * FROM pedido_items WHERE pedidoId = :pedidoId")
    suspend fun getPedidoItems(pedidoId: Long): List<PedidoItem>

    @Query("SELECT * FROM productos WHERE id IN (:productIds)")
    suspend fun getProductosByIds(productIds: List<Long>): List<Producto>
}
